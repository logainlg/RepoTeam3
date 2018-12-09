import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReadDocument {
    private String path;
    private ArrayList<String> arrayListPDF = new ArrayList<>();

    ReadDocument(String path) {
        this.path = path;
    }

    public void readPDF() throws IOException {
        // Getting directory Path
        File directoryPath = new File(path);
        // Storing all pdf document absolute path in array
        File[] pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        // Printing all available pdf document absolute path that store in File array
        System.out.println("$---------- Detected PDF File ----------$");
        Arrays.stream(pdfFile).map(File::getAbsolutePath).forEach(System.out::println);

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threads/2);

        for (File file : pdfFile) {
            PDDocument document = PDDocument.load(file);
            Future<String> futureString = executorService.submit(callableString);
            try {
                arrayListPDF.add(futureString.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            document.close();
        }
        /*
        for (File file : pdfFile) {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(document);
            arrayListPDF.add(text);
            document.close();
        }
        */
        executorService.shutdown();
        //arrayListPDF.forEach(System.out::println);
    }

    Callable<String> callableString = () -> {
        PDFTextStripper textStripper = new PDFTextStripper();
        String result = textStripper.getText(document);
        return result;
    };

    public ArrayList<String> getArrayListPDF() {
        return arrayListPDF;
    }

}
