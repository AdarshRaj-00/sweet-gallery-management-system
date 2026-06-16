import React from "react";

function SkeletonCard() {
  return (
    <div className="skeleton-card">
      <div className="skeleton-image">
        <div className="shimmer-wrapper"></div>
      </div>
      <div className="skeleton-text">
        <div className="shimmer-wrapper"></div>
      </div>
      <div className="skeleton-text short">
        <div className="shimmer-wrapper"></div>
      </div>
      <div className="skeleton-text short" style={{ width: "40%" }}>
        <div className="shimmer-wrapper"></div>
      </div>
      <div className="skeleton-btn">
        <div className="shimmer-wrapper"></div>
      </div>
    </div>
  );
}

export default SkeletonCard;
