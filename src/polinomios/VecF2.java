/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polinomios;
import javax.swing.*;

/**
 *
 * @author sala311
 */
public class VecF2{
    private int n;
    private float vec[];
    
    public VecF2(int cantTerminos)
    {
        n=cantTerminos*2+1;
        vec=new float[n];
        vec[0]=cantTerminos;
    }
    
    public void mostrar()
    {
        String imprimir="";
        for(int k=1;k<vec[0]*2;k+=2){
            if(vec[k+1]>0 && k>2)
                imprimir+="+"+vec[k+1]+"X^"+vec[k];
            else
                imprimir+=vec[k+1]+"X^"+vec[k];
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
        for(k=1;k<vec[0]+1;k+=2)
            res=res+vec[k+1]*(float)Math.pow(x, vec[k]);
        return res;
    }
    
    public void redimensionar()
    {
        int k;
        n=n*2;
        float aux[]=new float[n];
        for(k=0;k<=vec[0]*2;k++)
            aux[k]=vec[k];
        vec=aux;
    }
    
    public void almacenarTerm(float coef,int exp)
    {
        int k=1, j;
        while(k<=vec[0]*2 && vec[k]>exp)
            k=k+2;
        if(k<=vec[0]*2 && vec[k]==exp && vec[k+1]!=0)
            JOptionPane.showMessageDialog(null, "Ya existe un término con el mismo exponente");
        else{
            for(j=(int)vec[0]*2-1;j>k;j--)
                vec[j+1]=vec[j-1];
            vec[k]=exp;
            vec[k+1]=coef;
        }
    }
    
    public void insertarTerm(float coef,int exp)
    {
        int k=2, j;
        while(k<=vec[0]*2 && vec[k]>exp && vec[k+1]!=coef)
            k=k+2;
        if(k<=vec[0]*2 && vec[k]==exp){
            if(vec[k+1]+coef!=0)
                vec[k+1]=vec[k+1]+coef;
            else{
                for(j=k;j<=vec[0]*2;j+=2){
                    vec[j]=vec[j+2];
                    vec[j+1]=vec[j+3];
                }
                vec[0]=vec[0]-1;
            }
        }
        else{
            if(vec[0]*2==n)
                this.redimensionar();
            for(j=(int)vec[0]*2;j>=k;j--)
                vec[j+2]=vec[j];
            vec[k]=exp;
            vec[k+1]=coef;
            vec[0]=vec[0]+1;
        }
    }
    
    public void eliminarTerm()
    {
        
    }
    
    public VecF2 sumar(VecF2 b)
    {
        int k=2,j=2,expA,expB,coeA,coeB;
        VecF2 r=new VecF2(n);
        while(k<=vec[0]*2 && j<=b.getDato(0)*2)
        {
            expA=(int)vec[k];
            expB=(int)b.getDato(j);
            coeA=(int)vec[k+1];
            coeB=(int)b.getDato(j+1);
            if(expA==expB)
            {
                if (expA+expB!=0) 
                    r.insertarTerm(coeA+coeB, expA);
                k=k+2;
                j+=2;
            }
            else
            {
                if(expA>expB)
                {
                    r.insertarTerm(coeA, expA);
                    k+=2;
                }
                else
                {
                    r.insertarTerm(coeB, expB);
                    j+=2;
                }
            }
        }
        while(k<=vec[0]*2){
            r.insertarTerm((int)vec[k+1],(int)vec[k]);
            k=+2;
        }
        while(j<=b.getDato(0)*2){
            r.insertarTerm((int)b.getDato(j+1),(int)b.getDato(j));
        }
        return r;
    }
    
    public VecF2 multiplicar(VecF2 b)
    {
        int k,j,expA,expB,expR;
        float coefR;
        VecF2 r=new VecF2((int)vec[0]+(int)b.getDato(0));
        r.setDato(vec[0]+b.getDato(0),0);
        for(j=1;j<=b.getDato(0)*2;j+=2)
        {
            expB=(int)b.getDato(0);
            for(k=1;k<=vec[0]*2;k+=2)
            {
                expA=(int)vec[k];
                expR=expA+expB;
                coefR=vec[k+1]*b.getDato(j+1);
                r.insertarTerm(coefR,expR);
            }
        }
        return r;
    }

    public VecF2 dividir(VecF2 b)
    {
        /*
        if(vec[0]>=b.getDato(0))
        {
            int k,expR,posR,expA,posA;
            float coefR,coefA;
            VecF2 copia=this.hacerCopia();
            VecF2 r=new VecF2((int)vec[0]-(int)b.getDato(0));
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
*/
        return null;
    }

    
    public void comparar(VecF2 b)
    {
        
    }
    
    public VecF2 hacerCopia()
    {
        int k;
        VecF2 copia=new VecF2((int)vec[0]);
        for(k=2;k<=vec[0]+1;k++)
            copia.setDato(vec[k], k);
        return copia;
    }
}
