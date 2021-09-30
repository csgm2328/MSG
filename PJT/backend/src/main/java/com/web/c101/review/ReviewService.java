package com.web.c101.review;

import com.web.c101.error.CustomException;
import com.web.c101.error.ErrorCode;
import com.web.c101.file.ImgFile;
import com.web.c101.file.ImgFileDao;
import com.web.c101.member.Member;
import com.web.c101.member.MemberDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ReviewService {

    ReviewDao reviewdao;
    MemberDao memberdao;
    ImgFileDao imgFileDao;

    public boolean addReview(ReviewDto req) {

        Review review = ReviewAdaptor.dtoToEntity(req);

        try{
            reviewdao.save(review);

            String path = "//home//ubuntu//upload";
            File Folder = new File(path);
            if(!Folder.exists()) Folder.mkdir();

            String fileName;
            MultipartFile[] multipartFiles;

            if(req.getMultipartFiles() != null) {//파일이 존재할 때에만,
                multipartFiles = req.getMultipartFiles();

                for (int i = 0; i < multipartFiles.length; i++) {

                    MultipartFile multipartFile = multipartFiles[i];
                    UUID uuid = UUID.randomUUID();

                    fileName = uuid.toString()+"_"+multipartFile.getOriginalFilename();
                    multipartFile.transferTo(new File("//home//ubuntu//upload"+"//"+fileName));

                    ImgFile file = new ImgFile();//이미지 파일 세팅
                    file.setFile_name(fileName);
                    file.setFile_size(Long.toString(multipartFile.getSize()));
                    file.setRid(review.getRid());

                    imgFileDao.save(file);
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean delReview(long rid) {

        Optional<Review> reviewOpt = reviewdao.findReviewByRid(rid);

        if(reviewOpt.isPresent()) {

            // rid에 해당하는 review를 찾고 그 flag값을 false로 변경한다.
            Review review = reviewOpt.get();
            review.setFlag(false);

            reviewdao.save(review);

            return true;
        } else {
            return false;
        }

    }

    public List<ReviewDto> getUserReview(long mid) {

        Optional<Member> member = memberdao.findMemberByMemberId(mid);
        List<ReviewDto> list = null;

        if(member.isPresent()) {

            List<Review> reviewList = reviewdao.findReviewByMid(mid);
            ReviewDto tmp;
            list = new ArrayList<>();

            for(Review R : reviewList) {
               tmp = ReviewAdaptor.entityToDto(R);
               list.add(tmp);
            }

        }

        return list;
    }

    public List<ReviewDto> getStoreReview(String dong, String store) {

        List<ReviewDto> list = null;

        try{

            List<Review> reviewList;

            if(dong == null){
                reviewList = reviewdao.findReviewByStore(store);
            } else {
                reviewList = reviewdao.findReviewByDongAndStore(dong, store);
            }

            ReviewDto tmp;
            list = new ArrayList<>();

            for(Review R  : reviewList) {
                tmp = ReviewAdaptor.entityToDto(R);
                list.add(tmp);
            }

        } catch (Exception e) {
            throw new CustomException(ErrorCode.KAKAO_LOGIN_EXCEPTION);
        }

        return list;
    }

}
