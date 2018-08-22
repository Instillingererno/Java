package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class LargeFileReader {
    private int curPage = 0;
    private int lineNumbers;
    private boolean endOfFile = false;

    private BufferedReader reader;

    LargeFileReader(int lineNumbers, String fileName) throws IOException {
        this.lineNumbers = lineNumbers;

        FileReader fileReader = new FileReader(fileName);
        reader = new BufferedReader(fileReader);
    }

    int getCurPage() {
        return curPage;
    }

    boolean isEndOfFile() {
        return endOfFile;
    }

    String readNextPage() {
        if(endOfFile) return null;
        if(reader == null) return "No file is choosen";
        curPage++;
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < lineNumbers; i++) {
            try {
                String line = reader.readLine();
                if(line != null) {
                    output.append(line);
                } else {
                    output.append("--- End of file ---");
                    endOfFile = true;
                    break;
                }
            } catch (IOException io) {
                return io.toString();
            } finally {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
