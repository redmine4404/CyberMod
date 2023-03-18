package redmine.cybermod.utils;

import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import net.minecraft.loot.IRandomRange;
import net.minecraftforge.common.ForgeMod;
import redmine.cybermod.CyberMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.ReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
 @OnlyIn(Dist.CLIENT)
public class customSplashes  {
     List<String> splash =new ArrayList<String>();
     Random random = new Random();

     public customSplashes() {
            splash.add("redmine4404 le bg");
            splash.add("ca va ?)");
            splash.add("Cyerbcraft trop bien");
            splash.add("UnE GaME ?");
            splash.add("pas de rage hein? (;");
     }



     @Nullable
     public String getSplash() {
         System.out.println(splash);
         return splash.get(random.nextInt(splash.size()));
     }
 }