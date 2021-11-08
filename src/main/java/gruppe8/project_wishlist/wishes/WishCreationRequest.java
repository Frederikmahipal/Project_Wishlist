package gruppe8.project_wishlist.wishes;

import org.springframework.web.multipart.MultipartFile;

public record WishCreationRequest(Long wishlistId,
                                  String title,
                                  String url,
                                  Double price,
                                  String note,
                                  MultipartFile image)
{ }
