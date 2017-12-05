/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

/**
 *
 * @author Akin Gunay
 */
public interface NormSubject {
    void registerNormObserver(NormObserver normObserver);
    void removeNormObserver(NormObserver normObserver);
    void notifyNormObservers();
}
