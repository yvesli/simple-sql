package dataAccess;

class Factory {

    static DatabaseHandler create() {
        return SingleDirDatabase.getInstance();
    }

}
