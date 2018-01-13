package com.baidu.aip;

import net.coobird.thumbnailator.Thumbnails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangyunliang
 * @date 2018/1/12 0012 16:03
 */
public class BaiduAipUtils {
	
	// 传入可选参数调用接口
	public static final HashMap<String, String> OPTIONS = new HashMap<String, String>();
	
	// 百万英雄 矩形区域
	public static final Rectangle MILLION_HERO_RECTANGLE = new Rectangle(100, 300, 900, 900);

	// 默认识别区域
	public static final Rectangle DEFAULT_RECTANGLE = MILLION_HERO_RECTANGLE;
	
	static {
		// 识别语言类型，默认为CHN_ENG。可选值包括：
		// - CHN_ENG：中英文混合；
		// - ENG：英文；
		// - POR：葡萄牙语；
		// - FRE：法语；
		// - GER：德语；
		// - ITA：意大利语；
		// - SPA：西班牙语；
		// - RUS：俄语；
		// - JAP：日语；
		// - KOR：韩语；
		OPTIONS.put("language_type", "CHN_ENG");
		// 是否检测图像朝向，默认不检测，即：false。
		OPTIONS.put("detect_direction", "false");
		// 是否检测语言，默认不检测。即：false。
		OPTIONS.put("detect_language", "false");
		// 是否返回识别结果中每一行的置信度
		OPTIONS.put("probability", "false");
	}

	/**
	 * 按图片路径，识别整张图片
	 * @param path
	 * @return
     */
	public static String recognize(String path) {
		JSONObject res = BaiduAipOcr.getInstance().basicGeneral(path, OPTIONS);
		return processResult(res);
	}
	
	public static String recognizeRegion(String path) throws IOException {
		return recognizeRegion(path, DEFAULT_RECTANGLE);
	}

	/**
	 * 按图片路径，识别矩形区域
	 * @param path
	 * @param rectangle
	 * @return
	 * @throws IOException
     */
	public static String recognizeRegion(String path, Rectangle rectangle) throws IOException {
		final JSONObject result = BaiduAipOcr.getInstance().basicGeneral(region(path, rectangle), OPTIONS);
		return processResult(result);
	}

	/**
	 * 截取图片的矩形区域， byte[]二进制数组
	 * @param path
	 * @param rectangle
	 * @return
	 * @throws IOException
     */
	public static byte[] region(String path, Rectangle rectangle) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thumbnails
				.of(path)
				.sourceRegion(rectangle.x, rectangle.y, rectangle.width, rectangle.height)
				.size(rectangle.width, rectangle.height).toOutputStream(out);
		return out.toByteArray();
	}

	/**
	 * 处理识别结果
	 * @param result
	 * @return
     */
	private static String processResult(JSONObject result) {
		final JSONArray wordsResult = result.getJSONArray("words_result");
		StringBuilder ret = new StringBuilder();
		for (Object word : wordsResult) {
			ret.append(((JSONObject) word).getString("words")).append("\n");
		}
		return ret.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(BaiduAipUtils.recognizeRegion("D:\\__screenshot\\test.jpg"));
		} catch (IOException e) {
			System.err.println("未找到待识别图片！");
		}
	}
}
