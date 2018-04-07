package com.identity.service;

import com.identity.model.VaultValue;
import com.identity.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service("IndividualService")
public class IndividualService {
    public HashMap<String,String> realSsnIdentityMap = new HashMap<>();
    //0 for shortLivedToken and 1 for longLivedToken
    public HashMap<String,Integer> merchantCategory = new HashMap<>();
    public HashMap<String,VaultValue> merchantVault = new HashMap<>();

    public boolean isUserPresent(String name)
    {
        if(realSsnIdentityMap.containsKey(name))
        {
            return true;
        }
        return false;
    }
    public boolean registerUser(String name)
    {
        if(isUserPresent(name))
        {
            return false;
        }
        realSsnIdentityMap.put(name,""+Math.random()*10);
        return true;
    }

    public boolean removeUser(String name)
    {
        if(!isUserPresent(name))
        {
            return false;
        }
        else
        {
            realSsnIdentityMap.remove(name);
        }
        return true;
    }

    public boolean updateUser(String name)
    {
        if(!isUserPresent(name))
        {
            return false;
        }
        else
        {
            //call vault and change
        }
        return true;
    }

    public String createFakeSSN(String user, String merchant)
    {
        if(!isUserPresent(user))
        {
            return "Return message like the user not registered with vault";
        }
       // if(isMerchantPresent)
        if(merchantCategory.get(merchant)== Constants.LONG_LIVED_TOKEN)
        {
            //create a fakessn for user under param merchant in vault
            merchantVault.put(merchant,new VaultValue(user,"fakessnofvault",new Date()));
        }
        else
        {

        }
        return "value";
    }

}
