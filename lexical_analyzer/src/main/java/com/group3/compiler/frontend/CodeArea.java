    package com.group3.compiler.frontend;

    import javax.swing.*;
    import java.awt.dnd.*;
    import java.awt.datatransfer.*;
    import java.io.*;
    import java.util.List;

    public class CodeArea extends JTextArea {
        public CodeArea() {
            setLineWrap(true);
            enableDragAndDrop();
        }

        private void enableDragAndDrop() {
            new DropTarget(this, new DropTargetListener() {
                @Override
                public void drop(DropTargetDropEvent dtde) {
                    try {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY);
                        List<File> droppedFiles = (List<File>) dtde.getTransferable()
                            .getTransferData(DataFlavor.javaFileListFlavor);
                        
                        // Read the first file dropped
                        File file = droppedFiles.get(0);
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        read(reader, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // Other methods (dragEnter, dragExit, etc.) can be left empty
                public void dragEnter(DropTargetDragEvent dtde) {}
                public void dragOver(DropTargetDragEvent dtde) {}
                public void dropActionChanged(DropTargetDragEvent dtde) {}
                public void dragExit(DropTargetEvent dte) {}
            });
        }
    }