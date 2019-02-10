package team1359;

import org.opencv.core.*;
import java.util.Comparator;

public class ContourAreaComparator implements Comparator<RotatedRect> {
    @Override
        public int compare(RotatedRect o1, RotatedRect o2) {
            float area1 = (float)(o1.size.height * o1.size.width);
            float area2 = (float)(o2.size.height * o2.size.width);
            int result = Float.compare(area1, area2);
            return result;
        }
}