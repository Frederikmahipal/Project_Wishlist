package gruppe8.project_wishlist.wishlists;

import org.springframework.web.multipart.MultipartFile;

public record WishDeletionRequest(Long wishId,
                                  Long wishlistId,
                                  Double price,
                                  String note)
{ }
