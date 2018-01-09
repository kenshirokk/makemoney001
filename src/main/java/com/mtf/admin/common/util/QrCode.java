package com.mtf.admin.common.util;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class QrCode {

    private static final String QR_CODE_FOLDER = "qrcode";
    private static final String CHAR_SET = "utf-8";
    private static final String DEFAULT_FORMAT = "jpg";
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    private static final int DEFAULT_WORD_X = 100;
    private static final int DEFAULT_WORD_Y = 100;
    private static final int DEFAULT_FONT_SIZE = 30;

    /**
     * 生成二维码
     *
     * @param content
     * @param width
     * @param height
     * @param format
     * @param fileName
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static Path genQrCode(String content, int width, int height, String format, String fileName) throws
            WriterException, IOException {
        //配置参数
        Map<EncodeHintType, Object> hints = Maps.newHashMap();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHAR_SET);
        hints.put(EncodeHintType.MARGIN, 0);

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        Path path = Paths.get(QR_CODE_FOLDER + File.separator + fileName);
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
        return path;
    }

    /**
     * 添加中间logo
     *
     * @param logoUrl
     * @param path
     * @param format
     * @return
     * @throws IOException
     */
    public static Path addLogo(Path path, String logoUrl, String format) throws IOException {
        BufferedImage qrcode = ImageIO.read(path.toFile());
        BufferedImage logo = ImageIO.read(new URL(logoUrl));

        int logoWidth = qrcode.getWidth() / 4;
        int logoHeight = qrcode.getHeight() / 4;
        int logoX = (qrcode.getWidth() - logoWidth) / 2;
        int logoY = (qrcode.getHeight() - logoHeight) / 2;

        Graphics g = qrcode.getGraphics();
        g.drawImage(logo, logoX, logoY, logoWidth, logoHeight, null);
        g.dispose();
        File file = new File(QR_CODE_FOLDER + File.separator + path.getFileName());
        ImageIO.write(qrcode, format, file);
        return Paths.get(file.getPath());
    }

    /**
     * 添加文字
     *
     * @param path
     * @param content
     * @param format
     * @param x
     * @param y
     * @param fontSize
     * @return
     * @throws IOException
     */
    public static Path addWord(Path path, String content, String format, int x, int y, int fontSize) throws
            IOException {
        BufferedImage image = ImageIO.read(path.toFile());
        Graphics g = image.getGraphics();
        g.setFont(new Font("微软雅黑", Font.BOLD, fontSize));
        g.setColor(Color.BLACK);
        g.drawString(content, x, y);
        g.dispose();
        ImageIO.write(image, format, path.toFile());
        return path;
    }

    public static Path gen(String qrCodeContent, int qrWidth, int qrHeight, String format, String fileName, String
            logoUrl, String word, int wordX, int wordY, int fontSize) throws
            IOException, WriterException {
        Path path = genQrCode(qrCodeContent, qrWidth, qrHeight, format, fileName);
        path = addLogo(path, logoUrl, format);
        return addWord(path, word, format, wordX, wordY, fontSize);
    }

    public static Path gen(String qrCodeContent, String fileName, String logoUrl, String word) throws
            IOException, WriterException {
        return gen(qrCodeContent, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_FORMAT, fileName, logoUrl, word,
                DEFAULT_WORD_X, DEFAULT_WORD_Y, DEFAULT_FONT_SIZE);
    }
}
