import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CountEachCharacters implements CountEachCharactersInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountEachCharacters(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
        this.objectPDFArrayList = objectPDFArrayList;
    }

    @Override
    public HashMap<Character, Integer> charactersHashMap() {
        HashMap<Character, Integer> totalCharactersHashMap = new HashMap<>();
        for (int i = 0; i < objectPDFArrayList.size(); i++) {
            try {
                objectPDFArrayList.get(i).get().getCharacterHashMap().forEach((key, value) -> {
                    totalCharactersHashMap.merge(key, value, Integer::sum);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return totalCharactersHashMap;
    }

}