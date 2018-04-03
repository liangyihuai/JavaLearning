package com.huai.linearQuadtree;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LinearQuadtree {

    public static List<Integer> points = new ArrayList<Integer>();
    public int sideLength = 8;

    public LinearQuadtree(){
        for(int i = 1; i < 65; i++) points.add(i);
    }

    public LinearQuadtree(int totalNum){
        assert(totalNum > 0);
        int m = (int)Math.sqrt(totalNum);
        assert(m * m == totalNum);
        for(int i = 1; i <= totalNum; i++) points.add(i);
        this.sideLength = m;
    }

    double distance(Point point1, Point point2){
        return point1.distance(point2);
    }

    Point getCoordinateByValue(int value){
        int index = 0;

        for(int v: points){
            if(value == v) break;
            index++;
        }
        return getCoordinateByIndex(index);
    }

    
    Point getCoordinateByIndex(int index){
        Point point = new Point(0, 0);

        int startIndex = 0;
        int endIndex = points.size();
        while((endIndex - startIndex) > 1){
            int part = partIndex(startIndex, endIndex, index);
            int halfSide = (int)Math.sqrt(endIndex - startIndex) / 2;
            if(part == 1){
                point.y += halfSide;
            }
            else if(part == 2){
                point.x += halfSide;
            } else if (part == 3) {
                point.setLocation(point.getX() + halfSide, point.getY()+halfSide);
            }

            //update start index and end index
            int quarter =(endIndex - startIndex) / 4;
            if(part == 0) {
                endIndex = startIndex + quarter;
            }else if(part == 1){
                startIndex += quarter;
                endIndex = startIndex + quarter;
            }else if(part == 2){
                startIndex += 2 * quarter;
                endIndex = startIndex + quarter;
            }else if(part == 3){
                startIndex = endIndex - quarter;
            }
        }
        return point;
    }

    //return 0, 1, 2, 3
    int partIndex(int start, int end, int numIndex){
        assert((end - start) % 4 == 0);
        assert(numIndex >= start && numIndex < end);

        int quarter = (end - start) / 4;
        for(int i = 1; i <= 4; i++){
            if(numIndex < quarter * i + start) return i-1;
        }
        return -1;
    }

    public static void main(String[] args){
        int num = 16;
        LinearQuadtree test = new LinearQuadtree(num);

        System.out.println("Original linear data: size is = "+points.size());
        System.out.print("Display: [1, ..., "+points.size()+"]: ");
        for(int v: LinearQuadtree.points){
            System.out.print(v+",");
        }
        System.out.println();

        System.out.println("Build N-Order 2D diagram using original linear data: ");
        int  nOrderMatrix[][] = new int[test.sideLength][test.sideLength];
        for(int v: LinearQuadtree.points){
            Point point = test.getCoordinateByValue(v);
            nOrderMatrix[point.x][point.y] = v;
        }
        for(int i = test.sideLength - 1; i >=0; i--){
            for(int j = 0; j < test.sideLength; j++){
                int temp = nOrderMatrix[i][j];
                if(temp < 10)System.out.print("00"+temp+" ");
                else if(temp < 100)System.out.print("0"+temp+" ");
                else System.out.print(temp+" ");
            }
            System.out.println();
        }
    }
}
