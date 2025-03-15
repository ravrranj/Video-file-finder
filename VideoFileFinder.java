import java.io.File;

public class VideoFileFinder{
  
  //Array of common video file extension
    public static final String[] VIDEO_EXTENSIONS = {".mp4", ".mkv", ".avi", ".mov", ".flv"};
    public static int videoFileCount = 0;  //counter for video files
    public static final long MIN_FILE_SIZE_MB = 10; //minimmum size in MB

    public static void main(String args[]){
        
        //define the directory to search
        String directoryPath = "C:\\Users\\ravid\\Videos\\";

        File directory = new File(directoryPath);

        if(directory.exists() && directory.isDirectory()){
          System.out.println("Searching for video file in: " + directoryPath);
          listVideoFiles(directory); 
          System.out.println("\nTotal Video files found ( ≥ " + MIN_FILE_SIZE_MB + "MB): " + videoFileCount);
        
        } else {
          System.out.println("Invalid directory path!");
        }

    }
        //Recuresive functions to traverse directories
        public static void listVideoFiles(File directory){
          File[] files = directory.listFiles();

          if(files != null){
          for(File file : files) {
          if (file.isDirectory()) {
          //recursively search in subdirectories 
          listVideoFiles(file);
          } else if (isVideoFile(file) && isFileSizeValid(file)) {
          //print the full path and size of the video file
          long fileSizeInMB = file.length() / (1024 * 1024);
          System.out.println("Video File: " +  file.getAbsolutePath() + " | Size: " + fileSizeInMB + "MB");
          videoFileCount++; 
          
            }
           }
          }
        }

        // Function to check if a file is a video based on its extension
        private static boolean isVideoFile(File file){
          String fileName = file.getName().toLowerCase();
          for (String ext : VIDEO_EXTENSIONS) {
              if (fileName.endsWith(ext)) {
                 return true; 
          }
          }
                 return false;
        
        }

        //Function to check if file size is greater or equal to MIN_FILE_SIZE_MB
        private static boolean isFileSizeValid(File file){
            long fileSizeInMB = file.length() / (1024 * 1024);
            return fileSizeInMB >= MIN_FILE_SIZE_MB;
        }
    }