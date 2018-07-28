/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomios;

import javax.swing.JOptionPane;

/**
 *
 * @author Joxelito950
 */
public class Polinomios {

    /**
     * @param args the command line arguments
     */
    static VecF1 f11, f12, f13;
    static VecF2 f21, f22, f23;
    static Lista l1, l2, l3;

    public static void main(String[] args) {
        int op = 0, exp, pos, terminos;
        float coef;
        String menu = "0. Salir\n1. Crear Polinomio\n2. Mostrar Polinomio\n"
                + "3. Validar Polinomio\n4. ";
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "MENU", JOptionPane.PLAIN_MESSAGE));
            switch (op) {
                case 0:
                    break;
                case 1:
                    terminos = Integer.parseInt(JOptionPane.showInputDialog(null, "Los terminos del polinomio", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                    if (terminos < 100) {
                        if (terminos > 10) {
                            f21 = new VecF2(terminos);
                            for (int i = 0; i <= terminos; i++) {
                                coef = Float.parseFloat(JOptionPane.showInputDialog(null, "El coeficiente del termino " + (i + 1) + ".", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                                exp = Integer.parseInt(JOptionPane.showInputDialog(null, "El exponente del termino " + (i + 1) + ".", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                                f21.almacenarTerm(coef, exp);
                            }
                        } else {
                            exp = Integer.parseInt(JOptionPane.showInputDialog(null, "El grado del polinomio", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                            f11 = new VecF1(exp);
                            do {
                                coef = Float.parseFloat(JOptionPane.showInputDialog(null, "El coeficiente", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                                f11.almacenarTerm(coef, exp);
                                if (Integer.parseInt(JOptionPane.showInputDialog(null, "0. Terminar\n#. Ingresar otro termino", "Desea", JOptionPane.PLAIN_MESSAGE)) == 0) {
                                    break;
                                }
                                exp = Integer.parseInt(JOptionPane.showInputDialog(null, "El proximo exponente", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                            } while (true);
                        }
                    }
                    if (terminos >= 100) {
                        l1 = new Lista();
                        do {
                            coef = Float.parseFloat(JOptionPane.showInputDialog(null, "El coeficiente", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                            exp = Integer.parseInt(JOptionPane.showInputDialog(null, " el exponente", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                            l1.almacenarTerm(coef, exp);
                            if (Integer.parseInt(JOptionPane.showInputDialog(null, "0. Terminar\n#. Ingresar otro termino", "Desea", JOptionPane.PLAIN_MESSAGE)) == 0) {
                                break;
                            }
                        } while (true);
                    }
                    break;
                case 2:
                    if (f11 != null) {
                        f11.mostrar();
                    } else {
                        if (f21 != null) {
                            f21.mostrar();
                        } else {
                            if (l1 != null) {
                                l1.mostrar();
                            } else {
                                JOptionPane.showMessageDialog(null, "Primero debe crear un polinomio", "Falta Informacion", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 3:
                    coef = Float.parseFloat(JOptionPane.showInputDialog(null, "Real con el que realizara la validacion", "Ingrese", JOptionPane.PLAIN_MESSAGE));
                    if (f11 != null) {
                        JOptionPane.showMessageDialog(null, f11.evaluar(coef));
                    } else {
                        if (f21 != null) {
                            JOptionPane.showMessageDialog(null, f21.evaluar(coef));
                        } else {
                            if (l1 != null) {
                                JOptionPane.showMessageDialog(null, l1.evaluar(coef));
                            } else {
                                JOptionPane.showMessageDialog(null, "Primero debe crear un polinomio", "Falta Informacion", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion No Valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 0);
    }
}
