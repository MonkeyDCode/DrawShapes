/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.felconix.modelo;

import java.util.Arrays;

/**
 *
 * @author Jorge
 */
public class PuntosEditable {
    
    public int height;
    public int width;
    
    public int pathType, windingRule;
    public double[] p1,p2,p3;

    public PuntosEditable(int pathType, int windingRule, int height,int width, double[] p1, double[] p2, double[] p3) {
        this.pathType = pathType;
        this.windingRule = windingRule;
        this.height = height;
        this.width = width;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    @Override
    public String toString() {
        return "PuntosEditable{" + "pathType=" + pathType + ", windingRule=" + 
                windingRule + "\np1=" + Arrays.toString(p1) + "\np2=" + Arrays.toString(p2) + 
                "\np3=" + Arrays.toString(p3) + '}';
    }
    
    
    
}
