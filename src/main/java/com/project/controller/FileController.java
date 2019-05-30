
package com.project.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.common.ZConfig;
import com.project.common.ZDate;
import com.project.common.ZFile;
import com.project.dto.ValidDto;
import com.project.req.DownloadReq;
import com.project.req.UploadReq;
import com.project.rsp.SingleRsp;

/**
 * 
 * @author
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	// region -- Fields --

	private static final Logger _log = Logger.getLogger(FileController.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public FileController() {
		// ZConfig._allowUpload = true;
	}

	/**
	 * Download file from server
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/download")
	public ResponseEntity<InputStreamResource> download(@RequestBody DownloadReq req) {
		String fileName = "noname";
		Long len = 0l;
		InputStreamResource res = null;

		try {
			// Get data
			String path = req.getPath();
			String file = req.getFile();
			Boolean aws = req.getAws();

			String p = path + "/" + file;
			fileName = "attachment;filename=" + file;

			// Handle
			if (aws) {
				S3Object s3 = ZFile.download(p);
				//len = s3.getObjectMetadata().getInstanceLength();
				res = new InputStreamResource(s3.getObjectContent());
			} else {
				File f = new File(p);
				InputStream is = new FileInputStream(f);
				len = f.length();
				res = new InputStreamResource(is);
			}

		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		BodyBuilder bb = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, fileName);
		return bb.contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(len).body(res);
	}

	/**
	 * Upload for Angular (ScheduleOfOffer) (not use)
	 * 
	 * @param header
	 * @param file
	 * @param req
	 * @return
	 * @author ToanNguyen
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("req") String req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String originalName = file.getOriginalFilename();
			ObjectMapper mapper = new ObjectMapper();
			UploadReq o = mapper.readValue(req, UploadReq.class);

			// Handle
			ValidDto dto = validateFile(file, o);

			// Get extension
			int t = originalName.lastIndexOf(".") + 1;
			String extension = originalName.substring(t);

			// Create name
			String path = "img";
			String id = o.getId().toString();
			String today = ZDate.today().toString();
			String namef = "";
			namef = path  + "_" + id + "." + extension;

			// Upload file to S3
			//if (ZConfig._allowUpload) {
				InputStream in = file.getInputStream();
				dto.invoiceDataPath = ZFile.upload(in, namef, path);
			//}

			res.setResult("");

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	private ValidDto validateFile(MultipartFile file, UploadReq req) {
		ValidDto res = new ValidDto();

		// Get data
		return res;
	}

	// end

	// region -- Constants --


	// end
}