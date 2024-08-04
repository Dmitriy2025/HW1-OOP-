package family_tree.writer;

import java.io.Serializable;

public interface Writer {
    boolean saveFamilyTree(Serializable serializable);
    Object read();
    void setPath(String filePath);
}