package com.hemu.controller;

import java.io.*;
import java.util.*;

import org.apache.commons.csv.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileUploadingController {

	@GetMapping("/upload")
	public String uploadFile()
	{
		return "upload";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "0") int startRow,
            Model model) {
		List<Map<String, String>> data = new ArrayList<>();
		try {
			String fileType = file.getContentType();
			if (fileType != null && fileType.equals("text/csv")) {
				data = processCSV(file.getInputStream(), startRow);
			} else if (fileType != null
					&& fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				data = processExcel(file.getInputStream(), startRow);
			} else {
				model.addAttribute("error", "Unsupported file type");
				return "upload";
			}
		} catch (IOException e) {
			model.addAttribute("error", "File processing failed");
			return "upload";
		}
		model.addAttribute("data", data);
		return "result"; // Thymeleaf template to display processed data
	}

	private List<Map<String, String>> processCSV(InputStream inputStream, int startRow) throws IOException {
		List<Map<String, String>> data = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			List<CSVRecord> records = csvParser.getRecords();
			for (int i = startRow; i < records.size(); i++) {
				CSVRecord record = records.get(i);
				Map<String, String> row = new HashMap<>();
				for (String header : csvParser.getHeaderNames()) {
					row.put(header, record.get(header));
				}
				data.add(row);
			}
		}
		return data;
	}

	private List<Map<String, String>> processExcel(InputStream inputStream, int startRow) throws IOException {
		List<Map<String, String>> data = new ArrayList<>();
		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<String> headers = new ArrayList<>();

			if (rowIterator.hasNext()) {
				Row headerRow = rowIterator.next();
				for (Cell cell : headerRow) {
					headers.add(cell.getStringCellValue());
				}
			}

			int rowIndex = 0;
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (rowIndex++ < startRow)
					continue;
				Map<String, String> rowData = new HashMap<>();
				for (int i = 0; i < headers.size(); i++) {
					Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					rowData.put(headers.get(i), cell.toString());
				}
				data.add(rowData);
			}
		}
		return data;
	}

}
