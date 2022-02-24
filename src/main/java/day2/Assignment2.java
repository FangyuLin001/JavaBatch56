package day2;

import java.io.File;
import java.io.FileFilter;

public class Assignment2 {

    public static class Criteria {
        String path;
        boolean includeSubFolder;
        File[] subFolders;
        String extension;
        int folderCount;
        int fileCount;

        Criteria(String path) {
            this.path = path;
        }

        public void countSubFolder() {
            File file = new File(this.path);
            this.subFolders = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory();
                }
            });
            try {
                if(subFolders != null && subFolders.length > 0) {
                    includeSubFolder = true;
                }
            } catch(NullPointerException e) {
                e.printStackTrace();
            }
        }

        public int countFiles(File subPath){
            File file = new File(String.valueOf(subPath));
            File [] listFiles = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return !f.isDirectory();
                }
            });
            if(listFiles != null && listFiles.length > 0) {
                this.extension = listFiles[0].toString().split("\\.")[1];
                return listFiles.length;
            }
            return 0;
        }
    }

    public static void main(String [] args) {
        String filePath = "src/main/resources";
        Criteria files = new Criteria(filePath);

        if(files.countFiles(new File(filePath)) > 0) {
            int count = files.countFiles(new File(filePath));
            String extension = files.extension;
            System.out.println("Total files in path " + filePath + "is: " + count + "; and with the extension: " + extension);
        }

        files.countSubFolder();
        if(files.includeSubFolder) {
            File [] sub = files.subFolders;
            int n = 0;
            for(File subFolder : sub) {
                int count = files.countFiles(subFolder);
                String extension = files.extension;
                System.out.println("Total files in path " + subFolder + "is: " + count + "; and with the extension: " + extension);
            }
        }
    }
}
