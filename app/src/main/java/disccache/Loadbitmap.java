package disccache;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.Environment;

public class Loadbitmap {
	
	private final String DISK_CACHE_SUBDIR = "image";
	private final long DISK_CACHE_SIZE = 1024 * 1024 * 80; // MAX 192MB
	SimpleDiskCache cache;
	public Loadbitmap(Context context)
	{
		// Initialize disk cache on background thread
	    File cacheDir = getDiskCacheDir(context, DISK_CACHE_SUBDIR);
	    
		try {
			cache = SimpleDiskCache.open(cacheDir, getVersion(context), DISK_CACHE_SIZE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void putString(String key, String value)
	{
		if(cache!=null)
		{
			
			try {
				cache.put(key, value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getString(String key)
	{
		if(cache!=null)
		{
			try {
				return cache.getString(key).getString();
			} catch (IOException e) {
				return null;
			}
		}
		else
		{
			return null;
		}
	}
	public void clearDiskcache()
	{
		if(cache!=null)
		{
			try {
				cache.clear();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// Creates a unique subdirectory of the designated app cache directory. Tries to use external
	// but if not mounted, falls back on internal storage.
	private File getDiskCacheDir(Context context, String uniqueName) {
	    // Check if media is mounted or storage is built-in, if so, try and use external cache dir
	    // otherwise use internal cache dir
	    final String cachePath = context.getCacheDir().getPath();

	    return new File(cachePath + File.separator + uniqueName);
	}
	private int getVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return pInfo.versionCode;
        } catch (NameNotFoundException e) {
            return 0;
        }
    }
}
