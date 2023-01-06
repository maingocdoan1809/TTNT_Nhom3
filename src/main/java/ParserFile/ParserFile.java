/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ParserFile;

/**
 *
 * @author Admin
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import nhom3.huce.ttnt_nhom3.IDS;

public class ParserFile {
    private IDS graph;

    public ParserFile(File fileName) {
        graph = new IDS();
        Scanner scannerFile;
        try {
            scannerFile = new Scanner(new FileInputStream(fileName));
            while (scannerFile.hasNext()) {
            String rootName = scannerFile.nextLine();
            String children[] = scannerFile.nextLine().split(",");
            for (var child : children) {
                graph.addNode(rootName, child.strip());
            }
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public IDS getGraph() {
        return graph;
    }

}

