package com.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.review.entity.userReviewEntity;

@Repository
public interface UserReviewRepository extends JpaRepository<userReviewEntity, Long> {
	
	
		@Query(value = "SELECT * FROM USER_REVIEW r WHERE r.API_ID = :apiId ORDER BY r.REGDATE DESC", 
	           nativeQuery = true)
	    List<userReviewEntity> findReviewsByApiIdNative(@Param("apiId") Long apiId);
	    
	    //최신순 리뷰 5개
	    List<userReviewEntity> findTop5ByOrderByRegDateDesc();
	    
	    //최신순 리뷰 10개
	    List<userReviewEntity> findTop10ByOrderByRegDateDesc();
	    
	    List<userReviewEntity> findByUserEntity_UserId(Long userId);
	    
	    List<userReviewEntity> findByApiIdOrderByRegDateDesc(Long apiId);
	    
	    List<userReviewEntity> findByApiId(Long apiId);
	    
		Optional<userReviewEntity> findByReviewIdAndUserEntity_UserId(Long reviewId, Long userId);
		
		@EntityGraph(attributePaths = {"userEntity"})
		Optional<userReviewEntity> findByReviewId(Long reviewId);
		
		
		//관리자 회원의 리뷰 삭제
		void deleteByReviewId(Long reviewId);
		
		//리뷰 등록일 내림차순
		List<userReviewEntity> findByOrderByRegDateDesc();
		
		//유저Entity에 있는 닉네임을 %문자열% 로 찾음
		List<userReviewEntity> findByUserEntity_NicknameContaining(String RUS);
		
		@Transactional
		@Modifying 
		@Query("DELETE FROM userReviewEntity r WHERE r.userEntity.userId = :userId") 
		void deleteByUserEntityUserId(Long userId);


		
}
