package com.group3.compiler.frontend;

/* VIEW CLASS: Main Application Window
Description: The primary JFrame holding the Input, Output, and Stats panels.
Member Note: "Integrate the 'Tokenize' button listener here. It bridges the 
    JTextArea input to the Lexer backend." */

public class MainGUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainGUI.class.getName());

    public MainGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        MainPanel             = new javax.swing.JPanel();
        TopPanel              = new javax.swing.JPanel();
        Import                = new javax.swing.JButton();
        Tokenize              = new javax.swing.JButton();
        Clear                 = new javax.swing.JButton();
        BottomSection         = new javax.swing.JTabbedPane();
        TokenTableTab         = new javax.swing.JPanel();
        LexemeTableScrollPane = new javax.swing.JScrollPane();
        LexemeTable           = new javax.swing.JTable();
        UniqueTableScrollPane = new javax.swing.JScrollPane();
        UniqueTable           = new javax.swing.JTable();
        SymbolTableTab        = new javax.swing.JTabbedPane();
        OutputArea            = new javax.swing.JButton();
        LS_Editor             = new javax.swing.JPanel();
        RS_Tokens             = new javax.swing.JPanel();
        RS_Unique             = new javax.swing.JPanel();
        RS_Error              = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // ── Main panel: slate-blue body ──────────────────────────────────────
        MainPanel.setBackground(new java.awt.Color(107, 122, 141));   // #6B7A8D

        // ── Top bar: darker slate ────────────────────────────────────────────
        TopPanel.setBackground(new java.awt.Color(90, 106, 126));     // #5A6A7E
        TopPanel.setPreferredSize(new java.awt.Dimension(1920, 70));

        // ── Import button (📥 icon + label) ─────────────────────────────────
        Import.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14));
        Import.setText("\uD83D\uDCE5  Import File");   // 📥
        Import.setForeground(java.awt.Color.WHITE);
        Import.setBackground(new java.awt.Color(74, 111, 165));
        Import.setFocusPainted(false);
        Import.setBorderPainted(false);
        Import.setOpaque(true);
        Import.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Import.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Import.setIconTextGap(6);

        // ── Tokenize button (▶ icon + label) ────────────────────────────────
        Tokenize.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14));
        Tokenize.setText("\u25B6  Tokenize");          // ▶
        Tokenize.setForeground(java.awt.Color.WHITE);
        Tokenize.setBackground(new java.awt.Color(62, 142, 107));
        Tokenize.setFocusPainted(false);
        Tokenize.setBorderPainted(false);
        Tokenize.setOpaque(true);
        Tokenize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Tokenize.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Tokenize.setIconTextGap(6);

        // ── Clear button (🗑 icon + label) ───────────────────────────────────
        Clear.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14));
        Clear.setText("\uD83D\uDDD1  Clear");           // 🗑
        Clear.setForeground(java.awt.Color.WHITE);
        Clear.setBackground(new java.awt.Color(142, 74, 74));
        Clear.setFocusPainted(false);
        Clear.setBorderPainted(false);
        Clear.setOpaque(true);
        Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Clear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Clear.setIconTextGap(6);

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(Import,   javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Tokenize, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Clear,    javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Clear,    javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(Import,   javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tokenize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        // ── Bottom tabbed pane ────────────────────────────────────────────────
        BottomSection.setBackground(new java.awt.Color(210, 218, 230));
        BottomSection.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 16));

        // ── Token Table tab ───────────────────────────────────────────────────
        TokenTableTab.setBackground(new java.awt.Color(240, 242, 247));
        TokenTableTab.setPreferredSize(new java.awt.Dimension(1880, 300));

        // ── Lexeme table ──────────────────────────────────────────────────────
        LexemeTable.setBackground(new java.awt.Color(240, 242, 247));
        LexemeTable.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12));
        LexemeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[]{ "Lexeme", "Type", "Line", "Count" }
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        LexemeTable.setRowHeight(24);
        LexemeTable.setShowGrid(false);
        LexemeTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        LexemeTable.setSelectionBackground(new java.awt.Color(74, 111, 165));
        LexemeTable.setSelectionForeground(java.awt.Color.WHITE);
        styleTableHeader(LexemeTable);
        LexemeTableScrollPane.setViewportView(LexemeTable);
        LexemeTableScrollPane.getViewport().setBackground(new java.awt.Color(240, 242, 247));

        // ── Unique table ──────────────────────────────────────────────────────
        UniqueTable.setBackground(new java.awt.Color(240, 242, 247));
        UniqueTable.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12));
        UniqueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] {},
            new String[]{ "Unique Identifier", "Total" }
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        UniqueTable.setRowHeight(24);
        UniqueTable.setShowGrid(false);
        UniqueTable.setIntercellSpacing(new java.awt.Dimension(0, 0));
        UniqueTable.setSelectionBackground(new java.awt.Color(74, 111, 165));
        UniqueTable.setSelectionForeground(java.awt.Color.WHITE);
        styleTableHeader(UniqueTable);
        UniqueTableScrollPane.setViewportView(UniqueTable);
        UniqueTableScrollPane.getViewport().setBackground(new java.awt.Color(240, 242, 247));

        javax.swing.GroupLayout TokenTableTabLayout = new javax.swing.GroupLayout(TokenTableTab);
        TokenTableTab.setLayout(TokenTableTabLayout);
        TokenTableTabLayout.setHorizontalGroup(
            TokenTableTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TokenTableTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LexemeTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UniqueTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TokenTableTabLayout.setVerticalGroup(
            TokenTableTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TokenTableTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TokenTableTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UniqueTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(LexemeTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        BottomSection.addTab("Token Table",  TokenTableTab);
        BottomSection.addTab("Symbol Table", SymbolTableTab);

        // ── Output area button ────────────────────────────────────────────────
        OutputArea.setBackground(new java.awt.Color(240, 242, 247));
        OutputArea.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18));
        OutputArea.setText("OUTPUT AREA");
        OutputArea.setForeground(new java.awt.Color(44, 58, 75));
        OutputArea.setFocusPainted(false);
        OutputArea.setBorderPainted(false);

        // ── Left editor panel: glass white ────────────────────────────────────
        LS_Editor.setBackground(new java.awt.Color(245, 247, 252));
        LS_Editor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 210, 228), 1));

        javax.swing.GroupLayout LS_EditorLayout = new javax.swing.GroupLayout(LS_Editor);
        LS_Editor.setLayout(LS_EditorLayout);
        LS_EditorLayout.setHorizontalGroup(
            LS_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1086, Short.MAX_VALUE)
        );
        LS_EditorLayout.setVerticalGroup(
            LS_EditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1480, Short.MAX_VALUE)
        );

        // ── Right stat panels with titled borders (icon + label) ──────────────

        // Total Tokens panel — 🔢 icon
        RS_Tokens.setBackground(new java.awt.Color(245, 247, 252));
        RS_Tokens.setPreferredSize(new java.awt.Dimension(398, 200));
        RS_Tokens.setBorder(makeSidebarBorder("\uD83D\uDD22  Total Tokens"));   // 🔢

        javax.swing.GroupLayout RS_TokensLayout = new javax.swing.GroupLayout(RS_Tokens);
        RS_Tokens.setLayout(RS_TokensLayout);
        RS_TokensLayout.setHorizontalGroup(
            RS_TokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        RS_TokensLayout.setVerticalGroup(
            RS_TokensLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

        // Unique Identifiers panel — 🔑 icon
        RS_Unique.setBackground(new java.awt.Color(245, 247, 252));
        RS_Unique.setPreferredSize(new java.awt.Dimension(398, 200));
        RS_Unique.setBorder(makeSidebarBorder("\uD83D\uDD11  Unique Identifiers"));  // 🔑

        javax.swing.GroupLayout RS_UniqueLayout = new javax.swing.GroupLayout(RS_Unique);
        RS_Unique.setLayout(RS_UniqueLayout);
        RS_UniqueLayout.setHorizontalGroup(
            RS_UniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        RS_UniqueLayout.setVerticalGroup(
            RS_UniqueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

        // Error Count panel — ⚠ icon
        RS_Error.setBackground(new java.awt.Color(245, 247, 252));
        RS_Error.setPreferredSize(new java.awt.Dimension(398, 200));
        RS_Error.setBorder(makeSidebarBorder("\u26A0  Error Count"));   // ⚠

        javax.swing.GroupLayout RS_ErrorLayout = new javax.swing.GroupLayout(RS_Error);
        RS_Error.setLayout(RS_ErrorLayout);
        RS_ErrorLayout.setHorizontalGroup(
            RS_ErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        RS_ErrorLayout.setVerticalGroup(
            RS_ErrorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 107, Short.MAX_VALUE)
        );

        // ── Main panel layout (structure unchanged) ───────────────────────────
        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1550, Short.MAX_VALUE)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(LS_Editor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(RS_Tokens, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                            .addComponent(RS_Error,  javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                            .addComponent(RS_Unique, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)))
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(OutputArea,    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BottomSection, javax.swing.GroupLayout.DEFAULT_SIZE, 1510, Short.MAX_VALUE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LS_Editor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(RS_Tokens, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RS_Unique, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RS_Error,  javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(OutputArea,    javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BottomSection, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    /**
     * Creates a styled titled border for sidebar stat panels.
     * Displays an emoji icon + label in the Soft Glass palette.
     *
     * @param title  Text to show (include emoji prefix, e.g. "🔢  Total Tokens")
     * @return       A compound border: titled outer + line inner
     */
    private javax.swing.border.Border makeSidebarBorder(String title) {
        javax.swing.border.TitledBorder titled = javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 210, 228), 1),
            title
        );
        titled.setTitleFont(new java.awt.Font("Segoe UI Symbol", java.awt.Font.BOLD, 13));
        titled.setTitleColor(new java.awt.Color(44, 58, 75));
        titled.setTitleJustification(javax.swing.border.TitledBorder.LEFT);
        titled.setTitlePosition(javax.swing.border.TitledBorder.TOP);
        return titled;
    }

    /** Applies Soft Glass header styling to a table. */
    private void styleTableHeader(javax.swing.JTable table) {
        javax.swing.table.JTableHeader header = table.getTableHeader();
        header.setFont(new java.awt.Font("Franklin Gothic Medium", java.awt.Font.PLAIN, 12));
        header.setBackground(new java.awt.Color(216, 222, 233));
        header.setForeground(new java.awt.Color(44, 58, 75));
        header.setReorderingAllowed(false);
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(200, 208, 222)));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    // ── Variable declarations ─────────────────────────────────────────────────
    private javax.swing.JTabbedPane BottomSection;
    private javax.swing.JButton     Clear;
    private javax.swing.JButton     Import;
    private javax.swing.JPanel      LS_Editor;
    private javax.swing.JTable      LexemeTable;
    private javax.swing.JScrollPane LexemeTableScrollPane;
    private javax.swing.JPanel      MainPanel;
    private javax.swing.JButton     OutputArea;
    private javax.swing.JPanel      RS_Error;
    private javax.swing.JPanel      RS_Tokens;
    private javax.swing.JPanel      RS_Unique;
    private javax.swing.JTabbedPane SymbolTableTab;
    private javax.swing.JPanel      TokenTableTab;
    private javax.swing.JButton     Tokenize;
    private javax.swing.JPanel      TopPanel;
    private javax.swing.JTable      UniqueTable;
    private javax.swing.JScrollPane UniqueTableScrollPane;
}