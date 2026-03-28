package com.group3.compiler.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import com.group3.compiler.backend.models.Tokens;

/* VIEW CLASS: Main Application Window
Description: The primary JFrame holding the Input, Output, and Stats panels.
Fixes applied:
  - CodeInputArea initialized as CodeArea (drag-and-drop enabled)
  - Import button wired up with JFileChooser
  - ResultTable integrated (replaces inline updateOutputTables)
  - RS_Tokens, RS_Unique, RS_Error show big centered live counts
  - Error list displayed inside RS_Error panel after tokenizing
*/
public class MainGUI extends JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(MainGUI.class.getName());

    // ── Stat panel labels (updated after each tokenize run) ──────────────────
    private JLabel tokenCountLabel;
    private JLabel uniqueCountLabel;
    private JLabel errorCountLabel;
    // Error detail list shown inside RS_Error
    private JTextArea errorDetailArea;

    // ResultTable replaces the old inline table logic
    private ResultTable resultTable;

    public MainGUI() {
        initComponents();
        initButtonLogic();

        // FIX: Use CodeArea (drag-and-drop) instead of plain JTextArea
        CodeInputArea = new CodeArea();
        CodeInputArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        CodeInputArea.setBackground(new Color(245, 247, 252));
        CodeInputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Line numbers
        JTextArea lineNumbers = createLineNumberArea(CodeInputArea);
        JScrollPane scroll = new JScrollPane(CodeInputArea);
        scroll.setRowHeaderView(lineNumbers);
        scroll.setBorder(null);

        LS_Editor.setLayout(new BorderLayout());
        LS_Editor.add(scroll, BorderLayout.CENTER);
        LS_Editor.revalidate();

        // FIX: Wire up ResultTable into the existing scroll panes
        resultTable = new ResultTable();
        LexemeTableScrollPane.setViewportView(resultTable.getLexemeTable());
        UniqueTableScrollPane.setViewportView(resultTable.getUniqueTable());
        SymbolTableTab.add(resultTable.getRegexScrollPane(), BorderLayout.CENTER);

        // FIX: Add big centered count labels into stat panels
        tokenCountLabel  = makeBigLabel();
        uniqueCountLabel = makeBigLabel();
        errorCountLabel  = makeBigLabel();

        RS_Tokens.setLayout(new BorderLayout());
        RS_Tokens.add(tokenCountLabel, BorderLayout.CENTER);

        RS_Unique.setLayout(new BorderLayout());
        RS_Unique.add(uniqueCountLabel, BorderLayout.CENTER);

        // RS_Error: count on top, scrollable error list below
        errorDetailArea = new JTextArea();
        errorDetailArea.setEditable(false);
        errorDetailArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        errorDetailArea.setBackground(new Color(245, 247, 252));
        errorDetailArea.setForeground(new Color(180, 40, 40));
        JScrollPane errorScroll = new JScrollPane(errorDetailArea);
        errorScroll.setBorder(null);

        RS_Error.setLayout(new BorderLayout());
        RS_Error.add(errorCountLabel, BorderLayout.NORTH);
        RS_Error.add(errorScroll,     BorderLayout.CENTER);

        // Initialize all labels to zero
        updateStatLabels(0, 0, 0);
    }

    // ── Big centered number label factory ─────────────────────────────────────
    private JLabel makeBigLabel() {
        JLabel lbl = new JLabel("0", SwingConstants.CENTER);
        lbl.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 36));
        lbl.setForeground(new Color(44, 58, 75));
        return lbl;
    }

    private void updateStatLabels(int tokens, int unique, int errors) {
        tokenCountLabel.setText(String.valueOf(tokens));
        uniqueCountLabel.setText(String.valueOf(unique));
        errorCountLabel.setText(String.valueOf(errors));
    }

    private void initButtonLogic() {

        // ── CLEAR ─────────────────────────────────────────────────────────────
        Clear.addActionListener(e -> {
            CodeInputArea.setText("");
            resultTable.clear();
            com.group3.compiler.backend.SymbolTable.getInstance().reset();
            com.group3.compiler.utils.ErrorHandler.clear();
            errorDetailArea.setText("");
            updateStatLabels(0, 0, 0);
            logger.info("UI and Symbol Table cleared.");
        });

        // ── IMPORT (FIX: was unwired) ─────────────────────────────────────────
        Import.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Java / Text files", "java", "txt"));
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try (BufferedReader br = new BufferedReader(
                        new FileReader(fc.getSelectedFile()))) {
                    CodeInputArea.read(br, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Could not read file: " + ex.getMessage(),
                            "Import Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ── TOKENIZE ──────────────────────────────────────────────────────────
        Tokenize.addActionListener(e -> {
            String code = CodeInputArea.getText();
            if (code.trim().isEmpty()) return;

            // Run Lexer in background to keep GUI responsive
            new Thread(() -> {
                com.group3.compiler.backend.Lexer lexer =
                        new com.group3.compiler.backend.Lexer(code);
                List<Tokens> tokens = lexer.tokenize();

                // Build the two count maps that ResultTable.populate() needs
                Map<String, Integer> lexemeCounts    = new HashMap<>();
                Map<String, Integer> categoryCounts  = new LinkedHashMap<>();
                for (Tokens t : tokens) {
                    String lex = t.getLexeme();
                    String cat = t.getClass().getSimpleName().toUpperCase();
                    lexemeCounts.put(lex, lexemeCounts.getOrDefault(lex, 0) + 1);
                    categoryCounts.put(cat, categoryCounts.getOrDefault(cat, 0) + 1);
                }

                // Collect errors
                List<String> errors =
                        com.group3.compiler.utils.ErrorHandler.getErrors();

                int totalTokens  = tokens.size();
                int uniqueIds    = com.group3.compiler.backend.SymbolTable
                        .getInstance().getAllIdentifiers().size();
                int errorCount   = errors.size();

                // All UI updates must happen on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> {
                    resultTable.populate(tokens, lexemeCounts, categoryCounts);
                    updateStatLabels(totalTokens, uniqueIds, errorCount);

                    // Show error details
                    if (errorCount > 0) {
                        errorDetailArea.setText(String.join("\n", errors));
                    } else {
                        errorDetailArea.setText("No errors detected.");
                    }
                });
            }).start();
        });
    }

    // ── Line-number gutter ────────────────────────────────────────────────────
    private JTextArea createLineNumberArea(JTextArea textArea) {
        JTextArea lineNumbers = new JTextArea();
        lineNumbers.setBackground(new Color(220, 225, 235));
        lineNumbers.setEditable(false);
        lineNumbers.setFont(new Font("Monospaced",
                textArea.getFont().getStyle(), textArea.getFont().getSize()));
        lineNumbers.setForeground(new Color(100, 100, 100));
        lineNumbers.setColumns(4);
        lineNumbers.setCaretColor(lineNumbers.getBackground());

        Runnable update = () -> {
            int totalLines = Math.max(textArea.getLineCount(), 1);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= totalLines; i++) sb.append(i).append(System.lineSeparator());
            lineNumbers.setText(sb.toString());
        };

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate (javax.swing.event.DocumentEvent e) { update.run(); }
            public void removeUpdate (javax.swing.event.DocumentEvent e) { update.run(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update.run(); }
        });
        textArea.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) { update.run(); }
        });
        SwingUtilities.invokeLater(update);
        return lineNumbers;
    }

    // ── initComponents (layout unchanged, CodeInputArea declared at bottom) ───
    private void initComponents() {

        MainPanel             = new JPanel();
        TopPanel              = new JPanel();
        Import                = new JButton();
        Tokenize              = new JButton();
        Clear                 = new JButton();
        BottomSection         = new JTabbedPane();
        TokenTableTab         = new JPanel();
        LexemeTableScrollPane = new JScrollPane();
        LexemeTable           = new JTable();
        UniqueTableScrollPane = new JScrollPane();
        UniqueTable           = new JTable();
        SymbolTableTab        = new JPanel();
        OutputArea            = new JButton();
        LS_Editor             = new JPanel();
        RS_Tokens             = new JPanel();
        RS_Unique             = new JPanel();
        RS_Error              = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new Color(107, 122, 141));
        TopPanel.setBackground(new Color(90, 106, 126));
        TopPanel.setPreferredSize(new Dimension(1920, 70));

        Import.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        Import.setText("\uD83D\uDCE5  Import File");
        Import.setForeground(Color.WHITE);
        Import.setBackground(new Color(74, 111, 165));
        Import.setFocusPainted(false);
        Import.setBorderPainted(false);
        Import.setOpaque(true);
        Import.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Tokenize.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        Tokenize.setText("\u25B6  Tokenize");
        Tokenize.setForeground(Color.WHITE);
        Tokenize.setBackground(new Color(62, 142, 107));
        Tokenize.setFocusPainted(false);
        Tokenize.setBorderPainted(false);
        Tokenize.setOpaque(true);
        Tokenize.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Clear.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
        Clear.setText("\uD83D\uDDD1  Clear");
        Clear.setForeground(Color.WHITE);
        Clear.setBackground(new Color(142, 74, 74));
        Clear.setFocusPainted(false);
        Clear.setBorderPainted(false);
        Clear.setOpaque(true);
        Clear.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GroupLayout TopPanelLayout = new GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(44)
                .addComponent(Import,    GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addComponent(Tokenize,  GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(12)
                .addComponent(Clear,     GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(Clear,    GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(Import,   GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tokenize, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        BottomSection.setBackground(new Color(210, 218, 230));
        BottomSection.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 16));

        TokenTableTab.setBackground(new Color(240, 242, 247));
        TokenTableTab.setPreferredSize(new Dimension(1880, 300));

        // LexemeTable and UniqueTable are placeholders; ResultTable replaces their content
        LexemeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{ "Lexeme", "Category", "Line", "Col", "Occurrences" }
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        LexemeTable.setRowHeight(24);
        LexemeTable.setShowGrid(false);
        styleTableHeader(LexemeTable);
        LexemeTableScrollPane.setViewportView(LexemeTable);

        UniqueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{ "Category", "Count" }
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        UniqueTable.setRowHeight(24);
        UniqueTable.setShowGrid(false);
        styleTableHeader(UniqueTable);
        UniqueTableScrollPane.setViewportView(UniqueTable);

        GroupLayout TokenTableTabLayout = new GroupLayout(TokenTableTab);
        TokenTableTab.setLayout(TokenTableTabLayout);
        TokenTableTabLayout.setHorizontalGroup(
            TokenTableTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(TokenTableTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LexemeTableScrollPane, GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UniqueTableScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TokenTableTabLayout.setVerticalGroup(
            TokenTableTabLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, TokenTableTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TokenTableTabLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(UniqueTableScrollPane, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(LexemeTableScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        SymbolTableTab.setLayout(new BorderLayout());
        SymbolTableTab.setBackground(new Color(240, 242, 247));
        SymbolTableTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Column headers for initialization placeholder
        UniqueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{}, new String[]{ "Category", "Count" }
        ) { public boolean isCellEditable(int r, int c) { return false; } });
        UniqueTable.setRowHeight(24);

        BottomSection.addTab("Token Table",  TokenTableTab);
        BottomSection.addTab("Symbol Table", SymbolTableTab);

        OutputArea.setBackground(new Color(240, 242, 247));
        OutputArea.setFont(new Font("Franklin Gothic Book", Font.BOLD, 18));
        OutputArea.setText("OUTPUT AREA");
        OutputArea.setForeground(new Color(44, 58, 75));
        OutputArea.setFocusPainted(false);
        OutputArea.setBorderPainted(false);

        LS_Editor.setBackground(new Color(245, 247, 252));
        LS_Editor.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 228), 1));

        GroupLayout LS_EditorLayout = new GroupLayout(LS_Editor);
        LS_Editor.setLayout(LS_EditorLayout);
        LS_EditorLayout.setHorizontalGroup(
            LS_EditorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1086, Short.MAX_VALUE));
        LS_EditorLayout.setVerticalGroup(
            LS_EditorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 1480, Short.MAX_VALUE));

        RS_Tokens.setBackground(new Color(245, 247, 252));
        RS_Tokens.setPreferredSize(new Dimension(398, 200));
        RS_Tokens.setBorder(makeSidebarBorder("\uD83D\uDD22  Total Tokens"));

        RS_Unique.setBackground(new Color(245, 247, 252));
        RS_Unique.setPreferredSize(new Dimension(398, 200));
        RS_Unique.setBorder(makeSidebarBorder("\uD83D\uDD11  Unique Identifiers"));

        RS_Error.setBackground(new Color(245, 247, 252));
        RS_Error.setPreferredSize(new Dimension(398, 200));
        RS_Error.setBorder(makeSidebarBorder("\u26A0  Error Count"));

        GroupLayout RS_TokensLayout = new GroupLayout(RS_Tokens);
        RS_Tokens.setLayout(RS_TokensLayout);
        RS_TokensLayout.setHorizontalGroup(RS_TokensLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 412, Short.MAX_VALUE));
        RS_TokensLayout.setVerticalGroup(RS_TokensLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 107, Short.MAX_VALUE));

        GroupLayout RS_UniqueLayout = new GroupLayout(RS_Unique);
        RS_Unique.setLayout(RS_UniqueLayout);
        RS_UniqueLayout.setHorizontalGroup(RS_UniqueLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        RS_UniqueLayout.setVerticalGroup(RS_UniqueLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 107, Short.MAX_VALUE));

        GroupLayout RS_ErrorLayout = new GroupLayout(RS_Error);
        RS_Error.setLayout(RS_ErrorLayout);
        RS_ErrorLayout.setHorizontalGroup(RS_ErrorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
        RS_ErrorLayout.setVerticalGroup(RS_ErrorLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 107, Short.MAX_VALUE));
        
        // ── RIGHT PANEL CONTAINER (Sidebar) ───────────────────────────────
        JPanel rightPanelContainer = new JPanel();
        rightPanelContainer.setLayout(new GridLayout(3, 1, 0, 18));
        rightPanelContainer.setBackground(new Color(107, 122, 141));

        rightPanelContainer.add(RS_Tokens);
        rightPanelContainer.add(RS_Unique);
        rightPanelContainer.add(RS_Error);

        // ── JSplitPane (Editor + Sidebar) ─────────────────────────────────
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                LS_Editor,
                rightPanelContainer
        );
        splitPane.setResizeWeight(0.8); // 80% editor
        splitPane.setDividerSize(8);
        splitPane.setBorder(null);

        GroupLayout MainPanelLayout = new GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, GroupLayout.DEFAULT_SIZE, 1550, Short.MAX_VALUE)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(16)
                .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1510, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(OutputArea,    GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BottomSection, GroupLayout.DEFAULT_SIZE, 1510, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(38)
                .addComponent(TopPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(18)
                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18)
                .addComponent(OutputArea,    GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BottomSection, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                .addGap(13))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private javax.swing.border.Border makeSidebarBorder(String title) {
        javax.swing.border.TitledBorder titled = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 228), 1), title);
        titled.setTitleFont(new Font("Segoe UI Symbol", Font.BOLD, 13));
        titled.setTitleColor(new Color(44, 58, 75));
        titled.setTitleJustification(javax.swing.border.TitledBorder.LEFT);
        titled.setTitlePosition(javax.swing.border.TitledBorder.TOP);
        return titled;
    }

    private void styleTableHeader(JTable table) {
        javax.swing.table.JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
        header.setBackground(new Color(216, 222, 233));
        header.setForeground(new Color(44, 58, 75));
        header.setReorderingAllowed(false);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 208, 222)));
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    // ── Variable declarations ─────────────────────────────────────────────────
    private JTabbedPane BottomSection;
    private JButton     Clear;
    private JButton     Import;
    private JPanel      LS_Editor;
    private JTable      LexemeTable;
    private JScrollPane LexemeTableScrollPane;
    private JPanel      MainPanel;
    private JButton     OutputArea;
    private JPanel      RS_Error;
    private JPanel      RS_Tokens;
    private JPanel      RS_Unique;
    private JPanel      SymbolTableTab;
    private JPanel      TokenTableTab;
    private JButton     Tokenize;
    private JPanel      TopPanel;
    private JTable      UniqueTable;
    private JScrollPane UniqueTableScrollPane;
    private JTextArea   CodeInputArea; // Assigned to CodeArea in constructor
}