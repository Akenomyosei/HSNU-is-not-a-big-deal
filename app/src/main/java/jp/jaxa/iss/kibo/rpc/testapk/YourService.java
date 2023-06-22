package jp.jaxa.iss.kibo.rpc.defaultapk;


import android.util.Log;

import gov.nasa.arc.astrobee.Result;
import jp.jaxa.iss.kibo.rpc.api.KiboRpcService;
import jp.jaxa.iss.kibo.rpc.testapk.point;
/**
 * Class meant to handle commands from the Ground Data System and execute them in Astrobee
 * 看activated=>過去打1=>回原點=>過去打2=>回原點=>}五分鐘內重複
 */

public class YourService extends KiboRpcService {
    @Override
    protected void runPlan1(){
        // write your plan 1 here
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
    protected void runPlan2(){
        // write your plan 2 here
    }

    @Override
    protected void runPlan3(){
        // write your plan 3 here
    }

}

