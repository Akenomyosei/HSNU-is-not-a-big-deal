package jp.jaxa.iss.kibo.rpc.defaultapk;


import android.util.Log;

import gov.nasa.arc.astrobee.Result;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
import jp.jaxa.iss.kibo.rpc.testapk.point;
/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 * 看activated=>過去打1=>回原點=>過去打2=>回原點=>}五分鐘內重複}
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){
        // write your plan 1 here
        String tn = "hsnu is not a big deal";
        api.startMission(); //開始
        Log.i(tn,"mission start");
        int idnex[6][3]={{9.815 ,-9.806, 4.293},{11.2625 ,-10.58 ,5.3625},{10.513384 ,-9.085172 ,3.76203},{10.6031, -7.71007 ,3.76093},{9.866984 ,-6.673972, 5.09531},{11.102 ,-8.0304 ,5.9076},{12.023 ,-8.989 ,4.8305},{11.381944 ,-8.566172 ,3.76203}
            //target位置
        int qur[6][3]={{1 ,0 ,0 ,0},{0.707 ,0 ,0 ,0.707},{0 ,0 ,0 ,1},{0.707 ,0 ,0 ,0.707},{-0.5 ,0.5 ,-0.5 ,0.5},{1 ,0 ,0 ,0},{0.5 ,0.5 ,-0.5 ,-0.5},{0 ,0 ,0 ,1}}
            //quaternion
        int id_ls[2]={}
        while (true)
            // 獲得兩組target位置
            List<Integer> id_ls = api.getActiveTargets();
            //從起點移動到一點上
            Point point = new Point(idnex[id_ls[0]][0],idnex[id_ls[0]][1],idnex[id_ls[0]][2]);
            Quaternion quaternion = new Quaternion(qur[id_ls[0]][0],qur[id_ls[0]][1],qur[id_ls[0]][2]); //(x,y,z,w) w:cos(theta/2)
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to target1");
            // 拿到照片
            Mat image = api.getMatNavCam();
            api.saveMatImage(image,"nav.jpg");
            //發射雷射光
            Result r = api.laserControl(true);
            int numberTry = 3;
            while(r == null && numberTry != 0) {
                r = api.laserControl(true);
                numberTry--;
        }
            // 拍snapshots
            int target_id = 1;
            api.takeTargetSnapshot(target_id);
        
            //從target1移動到原點
            Point point = new Point(idnex[0][0],idnex[0][1],idnex[0][2]);
            Quaternion quaternion = new Quaternion(qur[0][0],qur[0][1],qur[0][2]); //(x,y,z,w) w:cos(theta/2)
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to start");

            //從起點移動到一點上
            Point point = new Point(idnex[id_ls[1]][0],idnex[id_ls[1]][1],idnex[id_ls[1]][2]);
            Quaternion quaternion = new Quaternion(qur[id_ls[1]][0],qur[id_ls[1]][1],qur[id_ls[1]][2]); //(x,y,z,w) w:cos(theta/2)
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to target2");
            // 拿到照片
            Mat image = api.getMatNavCam();
            api.saveMatImage(image,"nav.jpg");
            //發射雷射光
            Result r = api.laserControl(true);
            int numberTry = 3;
            while(r == null && numberTry != 0) {
                r = api.laserControl(true);
                numberTry--;
        }
            // 拍snapshots
            int target_id = 1;
            api.takeTargetSnapshot(target_id);

            // 查看所剩時間
            List<Long> timeRemaining = api.getTimeRemaining();
            // 時間是否夠
            if (timeRemaining.get(1) < 30000){
                break;
            }

    }  
            
        // 開手電筒
        api.flashlightControlFront(0.05f);

        
         //移動到QRcode上
            Point point = new Point(idnex[id_ls[6]][0],idnex[id_ls[6]][1],idnex[id_ls[6]][2]);
            Quaternion quaternion = new Quaternion(qur[id_ls[6]][0],qur[id_ls[6]][1],qur[id_ls[6]][2]); //(x,y,z,w) w:cos(theta/2)
        // 掃QRcode
        String mQrContent = 某函數?;

        // 關燈
        api.flashlightControlFront(0.00f);

        // 告知完成
        api.notifyGoingToGoal();

        //移動到goal上
            Point point = new Point(11.143d, -6.7607d, 4.9654d);
            Quaternion quaternion = new Quaternion(0, 0, -0.707, 0.707); //(x,y,z,w) w:cos(theta/2)
        // 告知完成任務
        api.reportMissionCompletion(mQrContent);  
            
            
        }
    }

    @Override
    protected void runPlan2(){
        // write your plan 2 here(orginal code)
        String tn = "hsnu is not a big deal";
        int loop_counter = 0;
        api.startMission(); //開始
        Log.i(tn,"mission start");

        while (true){
            // 獲得兩組target位置
            List<Integer> list = api.getActiveTargets();
            //從起點移動到一點上
            Point point = new Point(list[1]);
            Quaternion quaternion = new Quaternion(0f, 0f, 0f, 1f); //(x,y,z,w) w:cos(theta/2)
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to target1");

        //發射雷射光
        Result r = api.laserControl(true);

        int numberTry = 3;
        while(r == null && numberTry != 0) {
            r = api.laserControl(true);
            numberTry--;
        }
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
        String tn = "hsnu is not a big deal";
        api.startMission(); //開始
        Log.i(tn,"mission start");
        int id_ls[2]={}
        while (true)
            // 獲得兩組target位置
            List<Integer> id_ls = api.getActiveTargets();
            //從起點移動到一點上
            if (id_ls[0]=1) {
                api.moveTo(point.point1,point.quaternion1,false);
            }
            if (id_ls[0]=2) {
                api.moveTo(point.point2,point.quaternion2,false);
            }
            if (id_ls[0]=3) {
                api.moveTo(point.point3,point.quaternion3,false);
            }
            if (id_ls[0]=4) {
                api.moveTo(point.point4,point.quaternion4,false);
            }
            if (id_ls[0]=5) {
                api.moveTo(point.point5,point.quaternion5,false);
            }
            if (id_ls[0]=6) {
                api.moveTo(point.point6,point.quaternion6,false);
            }
            
                
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to target1");
            // 拿到照片
            Mat image = api.getMatNavCam();
            api.saveMatImage(image,"nav.jpg");
            //發射雷射光
            Result r = api.laserControl(true);
            int numberTry = 3;
            while(r == null && numberTry != 0) {
                r = api.laserControl(true);
                numberTry--;
        }
            // 拍snapshots
            int target_id = 1;
            api.takeTargetSnapshot(target_id);
        
            //從target1移動到原點
            Point point = new Point(9.815 ,-9.806, 4.293);
            Quaternion quaternion = new Quaternion(1 ,0 ,0 ,0); //(x,y,z,w) w:cos(theta/2)
            api.moveTo(point, quaternion, false);
            Log.i(teamName , "move to start");

            //從起點移動到一點上
            if (id_ls[0]=1) {
                api.moveTo(point.point1,point.quaternion1,false);
            }
            if (id_ls[0]=2) {
                api.moveTo(point.point2,point.quaternion2,false);
            }
            if (id_ls[0]=3) {
                api.moveTo(point.point3,point.quaternion3,false);
            }
            if (id_ls[0]=4) {
                api.moveTo(point.point4,point.quaternion4,false);
            }
            if (id_ls[0]=5) {
                api.moveTo(point.point5,point.quaternion5,false);
            }
            if (id_ls[0]=6) {
                api.moveTo(point.point6,point.quaternion6,false);
            }
            Log.i(teamName , "move to target2");
            // 拿到照片
            Mat image = api.getMatNavCam();
            api.saveMatImage(image,"nav.jpg");
            //發射雷射光
            Result r = api.laserControl(true);
            int numberTry = 3;
            while(r == null && numberTry != 0) {
                r = api.laserControl(true);
                numberTry--;
        }
            // 拍snapshots
            int target_id = 1;
            api.takeTargetSnapshot(target_id);

            // 查看所剩時間
            List<Long> timeRemaining = api.getTimeRemaining();
            // 時間是否夠
            if (timeRemaining.get(1) < 30000){
                break;
            }

    }  
            
        // 開手電筒
        api.flashlightControlFront(0.05f);

        
         //移動到QRcode上
            Point point = new Point(11.381944 ,-8.566172 ,3.76203);
            Quaternion quaternion = new Quaternion(0 ,0 ,0 ,1); //(x,y,z,w) w:cos(theta/2)
        // 掃QRcode
        String mQrContent = 某函數?;

        // 關燈
        api.flashlightControlFront(0.00f);

        // 告知完成
        api.notifyGoingToGoal();

        //移動到goal上
            Point point = new Point(11.143d, -6.7607d, 4.9654d);
            Quaternion quaternion = new Quaternion(0, 0, -0.707, 0.707); //(x,y,z,w) w:cos(theta/2)
        // 告知完成任務
        api.reportMissionCompletion(mQrContent);  
            
            
        }
    }

}

