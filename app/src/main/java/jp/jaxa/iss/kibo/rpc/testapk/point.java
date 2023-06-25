package jp.jaxa.iss.kibo.rpc.testapk;

import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;


public class point {
    public static Point point1 = new Point(11.2746,-9.92284,5.2988);
    public static Quaternion quaternion1 = new Quaternion(0.0f,0.0f,-0.707f,-0.707f);

    public static Point point2 = new Point(10.612,-9.0709,4.48);
    public static Quaternion quaternion2 = new Quaternion(0.5f,0.5f,-0.5f,0.5f);

    public static Point point3 = new Point(10.71,-7.7,4.48);
    public static Quaternion quaternion3 = new Quaternion(0.0f,0.707f,0.0f,0.707f);

    public static Point point4 = new Point(10.51,-6.7185,5.1804);
    public static Quaternion quaternion4 = new Quaternion(0.0f,0.0f,-1.0f,0.0f);

    public static Point point5 = new Point(11.114,-7.9756,5.3393);
    public static Quaternion quaternion5 = new Quaternion(-0.5f,-0.5f,-0.5f,0.5f);

    public static Point point6 = new Point(11.355,-8.5518,4.7818);
    public static Quaternion quaternion6 = new Quaternion(0.0f,0.0f,0.0f,1.0f);

    public static Point pointQ = new Point(11.369,-8.5518,4.48);
    public static Quaternion quaternionQ = new Quaternion(0.0f,0.707f,0.0f,0.707f);

    public static Point pointgoal = new Point(11.143d, -6.7607d, 4.9654d);
    public static Quaternion quaterniongoal = new Quaternion(0, 0, -0.707f, 0.707f); //(x,y,z,w) w:cos(theta/2)
}

