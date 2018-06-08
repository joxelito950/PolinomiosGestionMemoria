/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomios;

import javax.swing.JOptionPane;

/**
 *
 * @author sala311
 */
public class VecF1 {
    private int n;
    private float vec[];
    
    public VecF1(int grado)
    {
        n=grado+2;
        vec=new float[n];
        vec[0]=grado;
    }
    
    public void mostrar()
    {
        String imprimir="";
        for(int k=1;k<vec[0]+2;k++)
        {
            if(vec[k]!=0)
            {
                if(vec[k]>0 && k>1)
                    imprimir+= " + "+vec[k]+"x^"+(Integer.toString((int)vec[0]+1-k));
                else
                    imprimir+= vec[k]+"x^"+(Integer.toString((int)vec[0]+1-k));
            }
        }
        JOptionPane.showMessageDialog(null, imprimir);
    }
    
    public float getDato(int pos)
    {
        return vec[pos];
    }
    
    public void setDato(float dato, int pos)
    {
        vec[pos]=dato;
    }
    
    public int getTamaño()
    {
        return n;
    }
    
    public float evaluar(float x)
    {
        int k;
        float res=0;
        for(k=1;k<vec[0]+1;k++)
            res=res+vec[k]*(float)Math.pow(x, vec[0]+1-k);
        return res+vec[(int)vec[0]+1];
    }
    
    public void redimensionar(int exp)
    {
        int k,pos;
        n=exp+2;
        float aux[]=new float[n];
        for(k=2;k<=vec[0]+1;k++)
        {
            pos=exp+k-(int)vec[0];
            aux[pos]=vec[k];
        }
        vec=aux;
    }
    
    public void almacenarTerm(float coef,int exp)
    {
        int pos;
        if(exp<=vec[0] && exp>=0)
        {
            pos=(int)vec[0]+1-exp;
            if(vec[pos]==0)
                vec[pos]=coef;
            else
                JOptionPane.showMessageDialog(null, "Ya existe un termino con el mismo exponente");
        }
        else
            JOptionPane.showMessageDialog(null, "El exponente no corresponde al polinomio");
    }
    
    public void insertarTerm(float coef,int exp)
    {
        int pos;
        if(exp<0)
            JOptionPane.showMessageDialog(null, "Exponente no valido");
        else
            if(exp<=vec[0])
            {
                pos=(int)vec[0]+1-exp;
                vec[pos]=vec[pos]+coef;
                this.ajustar();
            }
            else
            {
                redimensionar(exp);
                vec[0]=exp;
                vec[2]=coef;
            }
    }
    
    public void eliminarTerm()
    {
        
    }
    
    public void ajustar()
    {
        int k,j=1,cont=0;
        while(j<=vec[0]+1 && vec[j]==0)
        {
            cont++;
            j++;
        }
        for(k=j;k<=vec[0]+1;k++)
            vec[k-cont]=vec[k];
        vec[0]=vec[0]-cont;
    }
    
    public VecF1 sumar(VecF1 b)
    {
        int k=1,j=1,my,expA,expB,posR;
        if(vec[0]>b.getDato(0))
            my=(int)vec[0];
        else
            my=(int)b.getDato(0);
        VecF1 r=new VecF1(my);
        while(k<=vec[0]+1 && j<=b.getDato(1)+1)
        {
            expA=(int)vec[0]+1-k;
            expB=(int)b.getDato(0)+1-j;
            if(expA==expB)
            {
                posR=(int)r.getDato(0)+1-expA;
                r.setDato(vec[k]+b.getDato(j), posR);
                k++;
                j++;
            }
            else
            {
                if(expA>expB)
                {
                    posR=(int)r.getDato(0)+1-expA;
                    r.setDato(vec[k], posR);
                    k++;
                }
                else
                {
                    
                    posR=(int)r.getDato(0)+1-expB;
                    r.setDato(b.getDato(j), posR);
                    j++;
                }
            }
        }
        r.ajustar();
        return r;
    }
    
    public VecF1 multiplicar(VecF1 b)
    {
        int k,j,expA,expB,expR,posR;
        float coefR;
        VecF1 r=new VecF1((int)vec[0]+(int)b.getDato(0));
        for(j=1;j<=b.getDato(0)+1;j++)
        {
            expB=(int)b.getDato(0)+1-j;
            for(k=1;k<=vec[0]+1;k++)
            {
                expA=(int)vec[0]+1-k;
                expR=expA+expB;
                coefR=vec[k]*b.getDato(j);
                posR=(int)r.getDato(0)+1-expR;
                r.setDato(r.getDato(posR)+coefR,posR);
            }
        }
        return r;
    }

    public VecF1 dividir(VecF1 b)
    {
        if(b!=null && vec[0]>=b.getDato(0))
        {
            int k,expR,posR,expA,posA;
            float coefR,coefA;
            VecF1 copia=this.hacerCopia();
            VecF1 r=new VecF1((int)vec[0]-(int)b.getDato(0));
            while(copia.getDato(0)>=b.getDato(0));
            {
                expR=(int)copia.getDato(0)-(int)b.getDato(0);
                coefR=copia.getDato(1)/b.getDato(1);
                posR=(int)r.getDato(0)+1-expR;
                r.setDato(coefR, posR);
                for(k=1;k<=b.getDato(0)+1;k++)
                {
                    expA=(int)b.getDato(0)+1-k+expR;
                    coefA=b.getDato(k)*coefR;
                    posA=(int)copia.getDato(0)+1-expA;
                    copia.setDato(copia.getDato(posA)*coefA, posA);
                    copia.ajustar();
                }
            }
            return r;
        }
        return null;
    }
    
    public void comparar(VecF1 b)
    {
        
    }
    
    public VecF1 hacerCopia()
    {
        int k;
        VecF1 copia=new VecF1((int)vec[0]);
        for(k=2;k<=vec[0]+1;k++)
            copia.setDato(vec[k], k);
        return copia;
    }
}
