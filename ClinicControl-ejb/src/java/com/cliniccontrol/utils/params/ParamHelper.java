/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliniccontrol.utils.params;

import java.util.ResourceBundle;

/**
 *
 * @author zeped
 */
public class ParamHelper {
    
    public static String getParametro(String paramName){
        String archivoConfiguracion = "com.cliniccontrol.utils.params.Params";
        ResourceBundle rb = ResourceBundle.getBundle(archivoConfiguracion);
        String param = "";
        try{
            param = rb.getString(paramName);
        }catch (Exception e){
            System.out.println("No se logro recuperar la informacion para el parametro: " + paramName);
        }
        return param;
    }
    
}
