package jp.jaxa.iss.kibo.rpc.defaultapk;

import android.util.Log;

import org.opencv.core.Mat;

import java.util.List;

import gov.nasa.arc.astrobee.Result;
import gov.nasa.arc.astrobee.types.Point;
import gov.nasa.arc.astrobee.types.Quaternion;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
import jp.jaxa.iss.kibo.rpc.testapk.QRCodeUtils;
import jp.jaxa.iss.kibo.rpc.testapk.point;

/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){
        // write your plan 1 here
        // write your plan 3 here
        String tn = "hsnu is not a big deal";
        api.startMission(); //開始
        Log.i(tn,"mission start");
        Mat imageMat = api.getMatNavCam();;// 从OpenCV加载的图像Mat对象
        String mQrContent = QRCodeUtils.readQRCode(imageMat);

        while (true){
            // 獲得兩組target位置
            int loop = 0;
            int n = 0;
            int target_id = 1;

            while(loop != 2) {
                List<Integer> list = api.getActiveTargets();
                //從起點移動到一點上
                if (list.get(n) == 1) {
                    api.moveTo(point.point1, point.quaternion1, false);
                }
                if (list.get(n) == 2) {
                    api.moveTo(point.point2, point.quaternion2, false);
                }
                if (list.get(n) == 3) {
                    api.moveTo(point.point3, point.quaternion3, false);
                }
                if (list.get(n) == 4) {
                    api.moveTo(point.point4, point.quaternion4, false);
                }
                if (list.get(n) == 5) {
                    api.moveTo(point.point5, point.quaternion5, false);
                }
                if (list.get(n) == 6) {
                    api.moveTo(point.point6, point.quaternion6, false);
                }


                Log.i(tn, "move to target"+ target_id);
                 // 拿到照片
                Mat image = api.getMatNavCam();
                api.saveMatImage(image, "nav"+ target_id +".jpg");
                n++;
                
                //發射雷射光
                Result r = api.laserControl(true);
                int numberTry = 3;
                while (r == null && numberTry != 0) {
                    r = api.laserControl(true);
                    numberTry--;

                }

                api.takeTargetSnapshot(target_id);
                target_id++;
                Point point = new Point(9.815 ,-9.806, 4.293);
                Quaternion quaternion = new Quaternion(1 ,0 ,0 ,0); //(x,y,z,w) w:cos(theta/2)
                api.moveTo(point, quaternion, false);
                Log.i(tn , "move to start");
                loop++;
            }
            //從target1移動到原點


            // 查看所剩時間
            List<Long> timeRemaining = api.getTimeRemaining();
            // 時間是否夠
            if (timeRemaining.get(1) < 30000){
                break;
            }
        }

        // 開手電筒
        api.flashlightControlFront(0.05f);

        api.moveTo(point.pointQ,point.quaternionQ,false);

         // 拿到照片
        Mat image = api.getMatNavCam();
        api.saveMatImage(image, "nav_QR.jpg");
        // 掃QRcode
        api.readQRCode(image)

        // 關燈
        api.flashlightControlFront(0.00f);

        // 告知完成
        api.notifyGoingToGoal();
        //移動到goal上
        api.moveTo(point.pointgoal,point.quaterniongoal,false);
        // 告知完成任務
        api.reportMissionCompletion(mQrContent);


    }
    private String yourMethod(){
        return "your method";
    }


    @Override
    protected void runPlan2(){
        // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

}

