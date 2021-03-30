package com.vanadis.lang.git;

import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;

/**
 * GitUtils
 *
 * @author yaoyuan
 * @date 2021/1/30 10:56 上午
 */
public class GitUtils {

    public static void main(String[] args) throws IOException {
        String url = "/Users/yaoyuan/Library/Application Support/Alfred/Alfred.alfredpreferences/.git";
        String remote = "https://github.com/VanadisGithub/Alfred.git";

        Git git = new Git(new FileRepository(url));
        git.add();
        git.commit().setMessage("1");
        git.push().setRemote(remote);
        git.close();
    }

}
