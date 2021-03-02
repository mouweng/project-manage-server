package zju.cst.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zju.cst.project.common.entity.JsonResult;
import zju.cst.project.common.enums.ResultCode;
import zju.cst.project.common.utils.ResultTool;
import zju.cst.project.entity.ProFile;
import zju.cst.project.entity.ProUser;
import zju.cst.project.service.FileService;
import zju.cst.project.service.ProjectService;
import zju.cst.project.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;
import java.util.List;

/**
 * @author: wengyifan
 * @description: 文件视图层
 * @date: 2021/3/1 1:22 下午
 */
@RestController
public class FileController {

    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    FileService fileService;
    @Value("${file.path}")
    private String path;

    /**
     * @param files
     * @param pid
     * @param principal
     * @return {@link JsonResult}
     * @throws
     * @author: wengyifan
     * @description: 文件上传
     * @date: 2021/3/1 7:30 下午
     */
    @PostMapping("/file/upload/{pid}")
    public JsonResult upload(@RequestParam("files") MultipartFile files[], @PathVariable("pid") Integer pid, Principal principal) {

        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 验证是否可以上传文件（验证这个人是否在这个项目中，在这个项目中才可以上传这个项目的文件）
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectUserByUidPid(principalUid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String filePath = path + pid + '/';

            // 重复上传
            if (fileService.repeatUpload(fileName, pid)) {
                return ResultTool.fail(ResultCode.FILE_UPLOAD_REPEAT);
            }

            File file1 = new File(System.getProperty("user.dir") + filePath);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            File dest = new File(System.getProperty("user.dir") + filePath, fileName);
            try {
                file.transferTo(dest);
                // 插入文件信息
                fileService.AddFile(fileName, filePath, pid, principalUser.getId());
            } catch (IOException e) {
                e.printStackTrace();
                return ResultTool.fail(ResultCode.FILE_UPLOAD_ERROR);
            }
        }
        return ResultTool.success("文件上传成功");
    }

    /**
     * @param id
     * @return {@link }
     * @throws
     * @author: wengyifan
     * @description: 文件下载
     * @date: 2021/3/1 7:30 下午
     */
    @GetMapping("/file/download/{pid}/{id}")
    public JsonResult download(HttpServletResponse response, @PathVariable("pid") Integer pid, @PathVariable("id") Integer id, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 验证是否可以下载文件（验证这个人是否在这个项目中，在这个项目中才可以下载这个项目的文件）
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectUserByUidPid(principalUid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }

        ProFile proFile = fileService.queryByIdAndPid(id, pid);
        // 判断文件是否存在
        if (proFile == null) {
            return ResultTool.fail(ResultCode.FILE_NOT_EXIST);
        }

        File file = new File(System.getProperty("user.dir") + proFile.getFilePath() + proFile.getFileName());

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + proFile.getFileName());

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultTool.fail(ResultCode.FILE_DOWNLOAD_ERROR);
        }
        // 文件下载次数增加
        fileService.downloadTimeAdd(proFile);
        // return ResultTool.success("文件下载成功");
        return null;
    }

    @GetMapping("/file/delete/{pid}/{id}")
    public JsonResult delete(@PathVariable("pid") Integer pid, @PathVariable("id") Integer id, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 验证是否可以删除文件（验证这个人是否在这个项目中）
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectUserByUidPid(principalUid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }

        // 判断文件是否存在
        ProFile proFile = fileService.queryByIdAndPid(id, pid);
        // 判断文件是否存在
        if (proFile == null) {
            return ResultTool.fail(ResultCode.FILE_NOT_EXIST);
        }

        // 文件删除
        File file = new File(System.getProperty("user.dir") + proFile.getFilePath() + proFile.getFileName());
        file.delete();

        fileService.deleteFile(proFile.getId());
        return ResultTool.success("文件删除成功");
    }

    // 按照项目查询文件
    @GetMapping("/file/getFilesByPid/{pid}")
    public JsonResult getFilesByPid(@PathVariable("pid") Integer pid, Principal principal) {
        if (principal == null) return ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        // 获取当前登录用户
        String principalUserName = principal.getName();
        ProUser principalUser = userService.selectByName(principalUserName);

        // 验证是否可以查询文件（验证这个人是否在这个项目中）
        Integer principalUid = principalUser.getId();
        if (!projectService.queryProjectUserByUidPid(principalUid, pid)) {
            return ResultTool.fail(ResultCode.NO_PERMISSION);
        }

        // 查询文件
        List<ProFile> proFileList = fileService.getFilesByPid(pid);
        return ResultTool.success(proFileList);
    }
}