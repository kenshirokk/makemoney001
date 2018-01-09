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

    /**
     * 生成二维码
     * @param content
     * @param width
     * @param height
     * @param format
     * @param fileName
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static Path genQrCode(String content, int width, int height, String format, String fileName) throws WriterException, IOException {
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
     * @param logoUrl
     * @param path
     * @param format
     * @return
     * @throws IOException
     */
    public static Path addLogo(String logoUrl, Path path, String format) throws IOException {
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
}
