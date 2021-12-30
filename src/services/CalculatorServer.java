/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.rmi.*;
import java.rmi.Naming.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author hassa
 */
public class CalculatorServer extends UnicastRemoteObject implements ICalculatorRemote{
    
    public CalculatorServer() throws RemoteException { super(); }

    @Override
    public double addition(double nb1, double nb2) throws RemoteException {
        return nb1+nb2;
    }

    @Override
    public double soustraction(double nb1, double nb2) throws RemoteException {
        return nb1-nb2;
    }

    @Override
    public double division(double nb1, double nb2) throws RemoteException {
        try {
            return nb1/nb2;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public double multiplication(double nb1, double nb2) throws RemoteException {
        return nb1*nb2;
    }
    
    public static void main(String args[]) throws AlreadyBoundException, MalformedURLException
	{
		try
		{
            LocateRegistry.createRegistry(1099);
			Naming.bind("rmi://localhost:1099/calculator", new CalculatorServer());
            System.out.println("Server started");
	    }
	    catch(RemoteException RE)
	    {
			System.out.println("Remote Server Error:"+ RE.getMessage());
		}
	}
    
}
