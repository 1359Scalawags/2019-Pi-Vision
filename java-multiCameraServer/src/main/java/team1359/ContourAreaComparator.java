package team1359;

import team1359.Main.MyPipeline;
import team1359.Network;
import java.util.ArrayList;
import org.opencv.core.*;
import org.opencv.imgproc.*;
import java.util.Comparator;

public class ContourAreaComparator implements Comparator<MatOfPoint> {
    @Override
        public int compare(MatOfPoint o1, MatOfPoint o2) {
            float area1 = (float)Imgproc.contourArea(o1);
            float area2 = (float)Imgproc.contourArea(o2);
            int result = Float.compare(area1, area2);
            return result;
        }
}