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

        MainPanel = new javax.swing.JPanel();
        TopPanel = new javax.swing.JPanel();
        Import = new javax.swing.JLabel();
        Tokenize = new javax.swing.JLabel();
        Clear = new javax.swing.JLabel();
        BottomSection = new javax.swing.JTabbedPane();
        TokenTableTab = new javax.swing.JPanel();
        LexemeTableScrollPane = new javax.swing.JScrollPane();
        LexemeTable = new javax.swing.JTable();
        UniqueTableScrollPane = new javax.swing.JScrollPane();
        UniqueTable = new javax.swing.JTable();
        SymbolTableTab = new javax.swing.JTabbedPane();
        OutputArea = new javax.swing.JButton();
        LS_Editor = new javax.swing.JPanel();
        RS_Tokens = new javax.swing.JPanel();
        RS_Unique = new javax.swing.JPanel();
        RS_Error = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(72, 83, 97));

        TopPanel.setBackground(new java.awt.Color(155, 161, 172));
        TopPanel.setPreferredSize(new java.awt.Dimension(1920, 70));

        Import.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        Import.setText("Import File");

        Tokenize.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        Tokenize.setText("Tokenize");

        Clear.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        Clear.setText("Clear");

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(Import, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tokenize, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(Import, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tokenize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        BottomSection.setBackground(new java.awt.Color(204, 203, 212));
        BottomSection.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 16)); // NOI18N

        TokenTableTab.setBackground(new java.awt.Color(200, 200, 209));
        TokenTableTab.setPreferredSize(new java.awt.Dimension(1880, 300));

        LexemeTable.setBackground(new java.awt.Color(204, 203, 212));
        LexemeTable.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12)); // NOI18N
        LexemeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Lexeme", "Type", "Line", "Count"
            }
        ));
        LexemeTableScrollPane.setViewportView(LexemeTable);

        UniqueTable.setBackground(new java.awt.Color(204, 203, 212));
        UniqueTable.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12)); // NOI18N
        UniqueTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Unique Identifier", "Total"
            }
        ));
        UniqueTableScrollPane.setViewportView(UniqueTable);

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

        BottomSection.addTab("Token Table", TokenTableTab);
        BottomSection.addTab("Symbol Table", SymbolTableTab);

        OutputArea.setBackground(new java.awt.Color(222, 223, 227));
        OutputArea.setFont(new java.awt.Font("Franklin Gothic Book", 1, 18)); // NOI18N
        OutputArea.setText("OUTPUT AREA");

        LS_Editor.setBackground(new java.awt.Color(222, 223, 227));

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

        RS_Tokens.setBackground(new java.awt.Color(222, 223, 227));
        RS_Tokens.setPreferredSize(new java.awt.Dimension(398, 200));

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

        RS_Unique.setBackground(new java.awt.Color(222, 223, 227));
        RS_Unique.setPreferredSize(new java.awt.Dimension(398, 200));

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

        RS_Error.setBackground(new java.awt.Color(222, 223, 227));
        RS_Error.setPreferredSize(new java.awt.Dimension(398, 200));

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
                            .addComponent(RS_Error, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                            .addComponent(RS_Unique, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)))
                    .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(OutputArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(RS_Error, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(OutputArea, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private javax.swing.JTabbedPane BottomSection;
    private javax.swing.JLabel Clear;
    private javax.swing.JLabel Import;
    private javax.swing.JPanel LS_Editor;
    private javax.swing.JTable LexemeTable;
    private javax.swing.JScrollPane LexemeTableScrollPane;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton OutputArea;
    private javax.swing.JPanel RS_Error;
    private javax.swing.JPanel RS_Tokens;
    private javax.swing.JPanel RS_Unique;
    private javax.swing.JTabbedPane SymbolTableTab;
    private javax.swing.JPanel TokenTableTab;
    private javax.swing.JLabel Tokenize;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JTable UniqueTable;
    private javax.swing.JScrollPane UniqueTableScrollPane;
}