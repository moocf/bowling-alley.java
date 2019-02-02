import java.util.*;
import java.io.*;


class BowlerFile {
  public String file;

  
  public BowlerFile(String file) {
    this.file = file;
  }
  
  public Bowler get(String nickname)
    throws IOException, FileNotFoundException {
    Bowler bowler = null;
    BufferedReader in = new BufferedReader(new FileReader(file));
    for(String line; bowler==null && (line=in.readLine())!=null;) {
      bowler = Bowler.parse(line);
      if(bowler!=null && !bowler.nickname.equals(nickname)) bowler = null;
    }
    in.close();
    return bowler;
  }

  public HashMap getAll()
    throws IOException, FileNotFoundException {
    HashMap bowlers = new HashMap();
    BufferedReader in = new BufferedReader(new FileReader(file));
    for(String line; (line=in.readLine())!=null;) {
      Bowler bowler = Bowler.parse(line);
      if(bowler!=null) bowlers.put(bowler.nickname, bowler);
    }
    in.close();
    return bowlers;
  }

  public void add(Bowler bowler)
    throws IOException, FileNotFoundException {
    RandomAccessFile out = new RandomAccessFile(file, "rw");
    out.skipBytes((int)out.length());
    out.writeBytes(bowler.stringify()+"\n");
    out.close();
  }
}
