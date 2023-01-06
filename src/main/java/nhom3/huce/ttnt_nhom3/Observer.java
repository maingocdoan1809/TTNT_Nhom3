/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom3.huce.ttnt_nhom3;

/**
 *
 * @author Admin
 */
public interface Observer {
    void observe(Subject subject);
    void remove(Subject subject);
    void notifySubject();
}
