package org.jclouds.glesys.domain;

import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
import com.google.gson.annotations.SerializedName;

/**
 * Operating system template
 *
 * @author Adam Lowe
 * @see <a href= "https://customer.glesys.com/api.php?a=doc#server_templates" />
 */
public class ServerTemplate implements Comparable<ServerTemplate>{

   public static Builder builder() {
      return new Builder();
   }

   public static class Builder {
      private String name;
      private int minDiskSize;
      private int minMemSize;
      private String os;
      private String platform;

      public Builder name(String name) {
         this.name = name;
         return this;
      }

      public Builder minDiskSize(int minDiskSize) {
         this.minDiskSize = minDiskSize;
         return this;
      }

      public Builder minMemSize(int minMemSize) {
         this.minMemSize = minMemSize;
         return this;
      }

      public Builder os(String os) {
         this.os = os;
         return this;
      }

      public Builder platform(String platform) {
         this.platform = platform;
         return this;
      }

      public ServerTemplate build() {
         return new ServerTemplate(name, minDiskSize, minMemSize, os, platform);
      }

      public Builder fromTemplate(ServerTemplate in) {
         return name(in.getName()).minDiskSize(in.getMinDiskSize()).minMemSize(in.getMinMemSize()).os(in.getOs()).platform(in.getPlatform());
      }

   }

   private final String name;
   @SerializedName("min_disk_size")
   private final int minDiskSize;
   @SerializedName("min_mem_size")
   private final int minMemSize;
   private final String os;
   private final String platform;

   public ServerTemplate(String name, int minDiskSize, int minMemSize, String os, String platform) {
      this.name = name;
      this.minDiskSize = minDiskSize;
      this.minMemSize = minMemSize;
      this.os = os;
      this.platform = platform;
   }

   public String getName() {
      return name;
   }

   /**
    * @return the minimum allowed disk size in GB
    * @see org.jclouds.glesys.domain.ServerAllowedArguments#getDiskSizes()
    */
   public int getMinDiskSize() {
      return minDiskSize;
   }

   /**
    * @return the minimum allowed memory size in MB
    * @see org.jclouds.glesys.domain.ServerAllowedArguments#getMemorySizes()
    */
   public int getMinMemSize() {
      return minMemSize;
   }

   /**
    * @return the name of the operating system type ex. "linux"
    */
   public String getOs() {
      return os;
   }

   /**
    * @return the name of the platform this template is available in, ex. "Xen"
    */
   public String getPlatform() {
      return platform;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }
      if (object instanceof ServerTemplate) {
         final ServerTemplate other = (ServerTemplate) object;
         return Objects.equal(name, other.name)
               && Objects.equal(platform, other.platform);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(name, platform);
   }

   @Override
   public String toString() {
      return String.format("[name=%s, min_disk_size=%d, min_mem_size=%d, os=%s, platform=%s]",
            name, minDiskSize, minMemSize, os, platform);
   }

   @Override
   public int compareTo(ServerTemplate arg0) {
      return Ordering.usingToString().compare(this, arg0);
   }
}
