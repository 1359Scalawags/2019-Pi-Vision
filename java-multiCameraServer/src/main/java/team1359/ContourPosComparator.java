package team1359;

import team1359.Main.MyPipeline;
import team1359.Network;
import java.util.ArrayList;
import org.opencv.core.*;
import org.opencv.imgproc.*;
import java.util.Comparator;

public class ContourPosComparator implements Comparator<RotatedRect>{

    @Override
    public int compare(RotatedRect o1, RotatedRect o2){
        float x1 = (float)o1.center.x;
        float x2 = (float)o2.center.x;
        int result = Float.compare(x1, x2);
        return result;
    }

}