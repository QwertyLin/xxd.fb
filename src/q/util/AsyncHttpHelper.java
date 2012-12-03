package q.util;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import x.asynchttp.AsyncHttpClient;
import x.asynchttp.AsyncHttpResponseHandler;

public class AsyncHttpHelper {
	
	public static final long 
		TIME_DAY = 1000 * 60 * 60 * 24,
		NO_CACHE = -1;
	
	public interface OnAsyncHttpListener {
		boolean onAsyncHttpVerify(String content);
		void onAsyncHttpSuccess(String content);
		void onAsyncHttpFailure(Throwable error, String content);
	}
	
	public AsyncHttpHelper(){
		mClient = new AsyncHttpClient();
	}

	private AsyncHttpClient mClient;
	
	public void get(final Context ctx, final String url, final long expireTime, final OnAsyncHttpListener listener){
		if(expireTime > 0){
			CacheSqilte sql = new CacheSqilte(ctx);
			sql.open(false);
			String cache = sql.query(url, expireTime);
			sql.close();
			if(cache != null){
				//System.out.println("cache");
				listener.onAsyncHttpSuccess(cache);
				return;
			}
			//System.out.println("no cache");
		}
		AsyncHttpResponseHandler mResponseHandler = new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				if(listener.onAsyncHttpVerify(content)){
					listener.onAsyncHttpSuccess(content);
					if(expireTime > NO_CACHE){
						CacheSqilte sql = new CacheSqilte(ctx);
						sql.open(true);
						sql.update(url, content);
						sql.close();
					}
				}else{
					listener.onAsyncHttpFailure(new Throwable("not through verify"), content);
				}
			};
			@Override
			public void onFailure(Throwable error, String content) {
				listener.onAsyncHttpFailure(error, content);
			}
		};
		mClient.get(url, mResponseHandler);
	}
	
	public static ByteArrayInputStream toStream(String str){
		return new ByteArrayInputStream(str.getBytes());
	}
	
	private class CacheSqilte extends SQLiteOpenHelper {
	   	
		public CacheSqilte(Context context) {
			super(context, "AsyncHttpCache.db", null, 1);
		}
		
		private SQLiteDatabase db;
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(
				"CREATE TABLE cache ("
				+ "url TEXT PRIMARY KEY,"
				+ "addtime LONG,"
				+ "content TEXT"
				+ ")"
			);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
			
		public void open(boolean writable) throws SQLException {
			if(writable){
				db = getWritableDatabase();
			}else{
				db = getReadableDatabase();
			}
		}
		
		public void close(){
			db.close();
			super.close();
		}
		
		private ContentValues buildContentValues(String url, String content) {
			ContentValues cv = new ContentValues();
			cv.put("url", md5(url));
			cv.put("addtime", Calendar.getInstance().getTimeInMillis());
			cv.put("content", content);
			return cv;
		}
			
		public void update(String url, String content) {
			db.replace("cache", null, buildContentValues(url, content));
		}
			
		public String query(String url, long expireTime) throws SQLException {
			Cursor cs = db.rawQuery("SELECT * FROM cache WHERE url = ? AND addtime >= ?", new String[]{md5(url), String.valueOf(Calendar.getInstance().getTimeInMillis() - expireTime)});
			if(cs.moveToNext()){
				return cs.getString(2);
			}else{
				return null;
			}
		}
		
		private String md5(String str) {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return str;
			}
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		}
			
		
	}
}
