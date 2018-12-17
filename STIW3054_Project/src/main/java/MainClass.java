import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClass {

    public static void main(String[] args) {
        int processor = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processor);
        ArrayList<Future<ObjectPDF>> futureArrayList = new ArrayList<>();
        File[] pdfFile;

        // File Directory
        File directoryPath = new File("D:\\UUM A181\\STIW3054\\Test File");
        pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        System.out.println("$---------- PDF File Found ----------$");
        Arrays.stream(pdfFile).map(File::getName).forEach(System.out::println);
        // Start Reading PDF using Executor
        for (int i = 0; i < pdfFile.length; i++) {
            ProcessPDF processPDF = new ProcessPDF(pdfFile[i].toString(), pdfFile[i].getName());
            Future<ObjectPDF> futureObjectPDF = executorService.submit(processPDF);
            futureArrayList.add(futureObjectPDF);
        }

        executorService.shutdown();
        /*
        System.out.println("$---------- Each File Detail ----------$");
        try {
            for (Future<ObjectPDF> objectPDFFuture : futureArrayList) {
                System.out.println("File Name : " + objectPDFFuture.get().getFileName());
                System.out.println("Words : " + objectPDFFuture.get().getWordsNumber());
                System.out.println("Characters : " + objectPDFFuture.get().getCharactersNumber());
                System.out.println("List : " + objectPDFFuture.get().getCharacterHashMap());
                System.out.println();
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        */
        CountWords countWords = new CountWords(futureArrayList);
        CountCharacters countCharacters = new CountCharacters(futureArrayList);
        CountEachCharacters countEachCharacters = new CountEachCharacters(futureArrayList);

        System.out.println("\n$---------- Total Detail ----------$");
        System.out.println("Number of Files : " + pdfFile.length);
        System.out.println("Total Words : " + countWords.calculateTotalWords());
        System.out.println("Total Characters : " + countCharacters.calculateTotalCharacters());
        countEachCharacters.charactersHashMap().forEach((key, value) -> System.out.print(key + ":" + value + " "));

        CountCommonsMath countCommonsMath = new CountCommonsMath(futureArrayList);
        double mean = countCommonsMath.countMean();
        double variance = countCommonsMath.countVariance();
        double standardDeviation = countCommonsMath.countSD();

        System.out.println("\n\n$---------- Commons Math ----------$");
        System.out.printf("Mean : %.2f", mean);
        System.out.printf("%nVariance : %.4f", variance);
        System.out.printf("%nStandard Deviation : %.4f", standardDeviation);

        CountZscore countZscore = new CountZscore();
        double zScore = countZscore.countZscore();
        System.out.printf("%nZ score : %.4f", zScore);

        GraphNormalization graphNormalization = new GraphNormalization();
        DrawBoxplot drawBoxplot = new DrawBoxplot();

        graphNormalization.normalizationGraph();
        drawBoxplot.boxplotGraph();
    }

}