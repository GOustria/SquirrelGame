package de.hsa.games.fatsquirrel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import de.hsa.games.fatsquirrel.core.EntityContext;

import java.lang.reflect.InvocationTargetException;

public class DebugHandler implements InvocationHandler  {
    private EntityContext context;
    
    public DebugHandler(EntityContext context)  { this.context = context; }
    
    public Object invoke(Object proxy, Method method, Object[] args)  {
            	
    	System.out.print("* calling method " + method + " with params ");
        for (int i = 0; i < args.length; i++) 
            System.out.print(" " + args[i]);
        System.out.println();

        Object result = null;
        try  {
            result = method.invoke(context, args);
        } catch(IllegalAccessException ex)  { 
        } catch(InvocationTargetException ex)  {
            System.out.println("* exception:" + ex.getTargetException());
            //throw ex.getTargetException();
        }
        
        System.out.println("* result:" + result);
        return result;
    }
}
