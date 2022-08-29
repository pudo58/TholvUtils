package org.tholv.SecurityUtils;

import org.tholv.Utils.StringUtils;

/**
 * Checkmail
 */
public class CheckMail {
    public static CheckMail getInstance(){
        return new CheckMail();
    }
    public synchronized boolean checkEmail(String email){
        if(email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
            return true;
        }
        throw new IllegalArgumentException("Email is not valid");
    }

    public synchronized boolean checkMailClone(String emailClone,String emailRoot){
        CheckMail checkMail = this.getInstance();
        if(emailClone.equals(emailRoot)){
            throw  new IllegalArgumentException("Email clone equals email root");
        }
        emailClone.trim();
        if(!(checkMail.checkEmail(emailClone)  && checkMail.checkEmail(emailRoot))){
            throw new IllegalArgumentException("Email is not valid");
        }
        if(emailClone.contains("+")){
            throw new IllegalArgumentException("Email is not valid");
        }
        String str="";
        String[] emailCloneSplit = emailClone.substring(0,emailClone.lastIndexOf("@")).split(".");
        str= StringUtils.concat(emailCloneSplit);
        if(str.equals(emailRoot.substring(0,emailRoot.lastIndexOf("@")))){
            throw new IllegalArgumentException("Email is exist");
        }
    return true;
    }

}
