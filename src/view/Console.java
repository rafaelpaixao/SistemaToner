/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Sistema;

/**
 *
 * @author Rafael
 */
public class Console {
    public static void main (String[] args){
        Sistema sistema = new Sistema();
        sistema.logar("joao", "123");
    }
}
