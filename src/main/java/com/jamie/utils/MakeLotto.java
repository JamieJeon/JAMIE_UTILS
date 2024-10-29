package com.jamie.ovo.plts.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toMap;

public class MakeLotto {
    private Random random = new Random();
    private int cnt;
    private int initTotCnt;
    private String filePath;
    private List<Lotto> list = new ArrayList<>();
    private int[] lowCon; // [하위 몇개, 몇개 뽑을건지]
    private int[] highCon; // [상위 몇개, 몇개 안뽑을건지]
    HashMap<Integer, Integer> numCntMap = new HashMap<>();
    private int[] conNumArr = new int[6];


    public MakeLotto(String filePath) throws Exception {
        this.filePath = filePath;
    }

    public void makeLotto(int cnt, int[] lowCon, int[] highCon) throws Exception {
        this.cnt = cnt;
        this.lowCon = lowCon;
        this.highCon = highCon;
        setList();
        getLottoNumber();
    }

    public void verifyLotto(int cnt, int[] lowCon, int[] highCon) throws Exception {
        this.cnt = cnt;
        this.lowCon = lowCon;
        this.highCon = highCon;
        setList();
        verifyLottoNumber();
    }

    private void setList() throws Exception {
        File lottoFile = new File(filePath);
        if(!lottoFile.exists()){
            throw new RuntimeException("파일 없음");
        }
        try{
            String line = "";
            //입력 스트림 생성
            FileReader filereader = new FileReader(lottoFile);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            while((line = bufReader.readLine()) != null){
                list.add(new Lotto(line));
            }
            bufReader.close();
            // 입력된 데이터를 분석하여 원하는 데이터 추출
            setDataAnal();
        } catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setDataAnal() {
        // 1. 데이터 사이즈
        this.initTotCnt = this.list.size();
        for(int i=0; i<list.size(); i++){
            // 2. 숫자 통계
            setNumArr(list.get(i).toString());

            // 3. 반복된 숫자 통계
            conNumArr[list.get(i).getConNum()] = conNumArr[list.get(i).getConNum()] + 1;
        }

        numCntMap = numCntMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    private void setNumArr(String line) {
        String[] sArr = line.split(",");
        for(int i=0; i<6; i++){
            int n = Integer.parseInt(sArr[i]);

            int cnt = 1;
            if(numCntMap.containsKey(n)) cnt += numCntMap.get(n);
            numCntMap.put(n,cnt);
        }
    }

    private void getLottoNumber() {
        for(int i=0; i<cnt; i++){
            String nums = getNewNums();
            boolean isExist = getAddData(nums);

            while(isExist){
                nums = getNewNums();
                isExist = getAddData(nums);
            }

            list.add(new Lotto(nums));
        }

        for(int i=initTotCnt; i<list.size(); i++){
            System.out.println(list.get(i).toString());
        }
    }

    private String getNewNums(){
        String result = "";
        int[] nums = new int[6];

        for(int i=0; i<nums.length; i++){
            int num = random.nextInt(45) + 1;
            boolean isExist = false;
            for(int j=0; j<nums.length; j++){
                if(!isExist && nums[j] != 0 && num == nums[j]){
                    isExist = true;
                }
            }

            while(isExist){
                isExist = false;
                num = random.nextInt(45) + 1;
                for(int j=0; j<nums.length; j++){
                    if(!isExist && nums[j] != 0 && num == nums[j]){
                        isExist = true;
                    }
                }
            }

            nums[i] = num;
        }

        nums = Arrays.stream(nums).sorted().toArray();

        for(int i=0; i<nums.length; i++){
            if(i!=0) result += ",";
            result += nums[i];
        }

        return result;
    }

    private boolean getAddData(String numStr) {
        boolean result = false;

        // 조건 1 , 기존 당첨 번호랑 일치하지 않는 숫자
        result = getDupInList(numStr);
        if(result) return result;

        // 조건 2 , 하위 랜덤 포함
        result = getLowRankAdd(numStr);
        if(result) return result;

        // 조건 3, 상위 최대 포함
        result = getHighRankAdd(numStr);
        if(result) return result;

        // 조건 4, 최근 30회에서 나오지 않은 숫자 반드시 3개 포함
        result = getLastNumAdd(numStr);
        if(result) return result;

        return result;
    }


    private boolean getDupInList(String numStr){
        boolean result = false;

        for(int i=0; i<list.size(); i++){
            if(numStr.equals(list.get(i).toString())) {
                return true;
            }
        }

        return result;
    }

    private boolean getLowRankAdd(String numStr){
        boolean result = false;

        String[] numStrArr = numStr.split(",");
        int size = lowCon[0];
        int cnt = lowCon[1];
        int[] lowRankArr = new int[size];
        int index = 0;
        for(Integer key : numCntMap.keySet()){
            lowRankArr[index] = key;
            index++;
            if(size == index) break;
        }

        for(int i=0; i<numStrArr.length; i++){
            for(int j=0; j<lowRankArr.length; j++){
                if(numStrArr[i].equals(lowRankArr[j]+"")) cnt--;
            }
        }

        if(cnt != 0){
            result = true;
        }

        return result;
    }

    private boolean getHighRankAdd(String numStr){
        boolean result = false;

        String[] numStrArr = numStr.split(",");
        int size = highCon[0];
        int cnt = highCon[1];
        int[] highRankArr = new int[size];
        int index = 44;
        for(Integer key : numCntMap.keySet()){
            if(index < size){
                highRankArr[index] = key;
            }
            index--;
        }

        for(int i=0; i<numStrArr.length; i++){
            for(int j=0; j<highRankArr.length; j++){
                if(numStrArr[i].equals(highRankArr[j]+"")) cnt--;
            }
        }

        if(cnt < 0){
            result = true;
        }

        return result;
    }

    private boolean getLastNumAdd(String numStr){
        boolean result = false;

        String[] numStrArr = numStr.split(",");
        HashSet<Integer> numList = new HashSet<>();
        int cnt = 2;
        for(int j=list.size()-1; j>list.size()-6; j--){
            numList.add(list.get(j).n1);
            numList.add(list.get(j).n2);
            numList.add(list.get(j).n3);
            numList.add(list.get(j).n4);
            numList.add(list.get(j).n5);
            numList.add(list.get(j).n6);
        }

        for(int i=0; i<numStrArr.length; i++){
            Iterator<Integer> it = numList.iterator();
            while (it.hasNext()){
                if(numStrArr[i].equals(it.next()+"")) cnt--;
            }
        }

        if(cnt < 0){
            result = true;
        }

        return result;
    }

    private void verifyLottoNumber() {
        String target = getNewNums();
        int winCnt = 0;
        int tryCnt = 0;
        while(winCnt < 10){
             tryCnt++;
            String nums = getNewNums();
            boolean isExist = getAddData(nums);

            while(isExist){
                nums = getNewNums();
                isExist = getAddData(nums);
            }

            if(nums.equals(target)) {
                System.out.println(String.format("%d번째 시도에서 당첨!!", tryCnt));
                winCnt++;
            }
        }
    }
}

class Lotto{
    int n1;
    int n2;
    int n3;
    int n4;
    int n5;
    int n6;

    public Lotto(String line){
        String[] sArr = line.split(",");
        this.n1 = Integer.parseInt(sArr[0]);
        this.n2 = Integer.parseInt(sArr[1]);
        this.n3 = Integer.parseInt(sArr[2]);
        this.n4 = Integer.parseInt(sArr[3]);
        this.n5 = Integer.parseInt(sArr[4]);
        this.n6 = Integer.parseInt(sArr[5]);
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%d,%d,%d,%d",n1,n2,n3,n4,n5,n6);
    }

    public int getConNum() {
        int con = 0;
        if(this.n1+1 == this.n2) con++;
        if(this.n2+1 == this.n3) con++;
        if(this.n3+1 == this.n4) con++;
        if(this.n4+1 == this.n5) con++;
        if(this.n5+1 == this.n6) con++;

        return con;
    }
}
