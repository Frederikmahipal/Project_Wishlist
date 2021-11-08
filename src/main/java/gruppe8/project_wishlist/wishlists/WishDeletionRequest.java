package gruppe8.project_wishlist.wishlists;

import org.springframework.web.multipart.MultipartFile;

public record WishDeletionRequest(Long wishlistId,
                                  String title,
                                  String url,
                                  Double price,
                                  String note)
{ }
