package extraction;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
 * GetJarResources permet de parcourir une archive jar et d'en extraire certaines ressources.
 * C'est utile pour la phase d'éxécution du jar, va directement chercher les ressources dans lui-même!
 * Inspiré de : JarResources :
 * http://www.javaworld.com/article/2077548/learn-java/java-tip-49--how-to-extract-java-resources-from-jar-and-zip-archives.html
 */

public final class GetJarResources {

   //Flag de débug.
   public boolean debugOn=false;

   // tables pour mappage du jar
   @SuppressWarnings("rawtypes")
   private Hashtable htSizes=new Hashtable();  
   @SuppressWarnings("rawtypes")
   private Hashtable htJarContents=new Hashtable();

   // LE jar 
   private String jarFileName;
   
   //Crée le jarResources, extrait les ressources dans les hastables internes, rangées par nom.
   public GetJarResources(String jarFileName) {
      this.jarFileName=jarFileName;
      init();
   }

   //Extrait une ressource du jar (en cherchant par le name)
   public byte[] getResource(String name) {
      return (byte[])htJarContents.get(name);
   }

   //Initialise les hash table avec les ressources du jar.
   @SuppressWarnings("unchecked")
   private void init() {
      try {
          ZipFile zf=new ZipFile(jarFileName);
          @SuppressWarnings("rawtypes")
          Enumeration e=zf.entries();
          while (e.hasMoreElements()) {
              ZipEntry ze=(ZipEntry)e.nextElement();
              if (debugOn) {
                 System.out.println(dumpZipEntry(ze));
              }
              htSizes.put(ze.getName(),new Integer((int)ze.getSize()));
          }
          zf.close();

          //Mise dans les hash tables
          FileInputStream fis=new FileInputStream(jarFileName);
          BufferedInputStream bis=new BufferedInputStream(fis);
          @SuppressWarnings("resource")
          ZipInputStream zis=new ZipInputStream(bis);
          ZipEntry ze=null;
          while ((ze=zis.getNextEntry())!=null) {
             if (ze.isDirectory()) {
                continue;
             }
             if (debugOn) {
                System.out.println(
                   "ze.getName()="+ze.getName()+","+"getSize()="+ze.getSize()
                   );
             }
             int size=(int)ze.getSize();

             if (size==-1) { //Taille inconnue
                size=((Integer)htSizes.get(ze.getName())).intValue();
             }
             byte[] b=new byte[(int)size];
             int rb=0;
             int chunk=0;
             while (((int)size - rb) > 0) {
                 chunk=zis.read(b,rb,(int)size - rb);
                 if (chunk==-1) {
                    break;
                 }
                 rb+=chunk;
             }
            
             //Ajout à la hash table interne
             htJarContents.put(ze.getName(),b);
             if (debugOn) {
                System.out.println(
                   ze.getName()+"  rb="+rb+
                   ",size="+size+
                   ",csize="+ze.getCompressedSize()
                   );
             }
          }
       } catch (NullPointerException e) {
          System.out.println("done.");
       } catch (FileNotFoundException e) {
          e.printStackTrace();
       } catch (IOException e) {
          e.printStackTrace();
       }
   }

   //Converti le zip d'entré en string
   private String dumpZipEntry(ZipEntry ze) {
       StringBuffer sb=new StringBuffer();
       if (ze.isDirectory()) {
          sb.append("d "); 
       } else {
          sb.append("f "); 
       }
       if (ze.getMethod()==ZipEntry.STORED) {
          sb.append("stored   "); 
       } else {
          sb.append("defalted ");
       }
       sb.append(ze.getName());
       sb.append("\t");
       sb.append(""+ze.getSize());
       if (ze.getMethod()==ZipEntry.DEFLATED) {
          sb.append("/"+ze.getCompressedSize());
       }
       return (sb.toString());
   }
}