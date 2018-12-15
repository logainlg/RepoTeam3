import java.util.ArrayList;
import java.util.concurrent.Future;

public class CountCommonsMath implements CountCommonsMathInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountCommonsMath(ArrayList<Future<ObjectPDF>> oobjectPDFArrayList) {
        this.objectPDFArrayList = oobjectPDFArrayList;
    }

    @Override
    public double countMean() {
        return 0;
    }

    @Override
    public double countVariance() {
        return 0;
    }

    @Override
    public double countSD() {
        return 0;
    }

}