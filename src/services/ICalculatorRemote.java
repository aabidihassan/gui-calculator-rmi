/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author hassa
 */
public interface ICalculatorRemote extends Remote{
    public double addition(double nb1, double nb2) throws RemoteException;
    public double soustraction(double nb1, double nb2) throws RemoteException;
    public double division(double nb1, double nb2) throws RemoteException;
    public double multiplication(double nb1, double nb2) throws RemoteException;
}
