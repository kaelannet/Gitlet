package gitlet;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Storage implements Serializable {

    /* Contains all changed filenames as KEY and their respective blob SHA1 ID as VALUE. */
    HashMap<String, String> add;

    /* Contains all removed filenames as KEY and their respective blob SHA1 ID as VALUE. */
    HashMap<String, String> remove;

    /* Contains all branchNames as KEY and a reference to the latest commit in branch as VALUE. */
    HashMap<String, String> branches;

    // Contains splitPoints. Format: "branch1branch2", commitID. (branch1 < branch2 lex)
    HashMap<String, ArrayList<String>> splitPoints;

    /* Name of the current branch. */
    String head;


    /* Creates our storage class that holds branches,commit references, removed, changed files */
    public Storage(String branchName) {
        this.add = new HashMap<>();
        this.remove = new HashMap<>();
        this.branches = new HashMap<>();
        this.splitPoints = new HashMap<>();
        this.head = branchName;
    }

    /* Changes head pointer to branchName */
    public void setHead(String branchName) {
        head = branchName;
    }

    /* Returns current commit. */
    public String getCommit() {
        return branches.get(head);
    }

    /* Serializes this Storage object after program runs and writes it to the gitlet
    directory and overwrites previous Storage file. */
    public void serialize(String path) {
        try {
            //Saving Storage in a gitlet directory and overwriting previous copy
            FileOutputStream commitFile = new FileOutputStream(path);
            ObjectOutputStream commitOut = new ObjectOutputStream(commitFile);

            //Serialize the Object (this is a method)
            commitOut.writeObject(this);

            commitOut.close();
            commitFile.close();

        } catch (IOException e) {
            System.out.println("IOException has occurred during "
                    + "StorageSerialize (and has been caught)");
        }

    }

    /* Returns the current storage object. */
    public static Storage deserialize(String path) {
        try {
            File inFile = new File(path);
            FileInputStream file = new FileInputStream(inFile);
            ObjectInputStream inp = new ObjectInputStream(file);

            Storage s = (Storage) inp.readObject();
            inp.close();
            return s;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException or ClassNotFoundException has"
                    + " occurred during StorageDeserialize (and has been caught)");
            return null;
        }
    }

}
